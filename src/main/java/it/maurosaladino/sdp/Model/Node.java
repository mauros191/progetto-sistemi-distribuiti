package it.maurosaladino.sdp.Model;

import java.util.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import it.maurosaladino.sdp.Proto.P2PServiceGrpc;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyResponse;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequest;
import it.maurosaladino.sdp.Proto.P2PServiceOuterClass.NotifyRequestDelete;
import it.maurosaladino.sdp.RestCall.NodeRestCall;
import it.unimi.simulator.Measurement;
import it.unimi.simulator.PM10Simulator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(
        {"ipGateway", "portGateway", "pm10Sim", "nodeList", "dx", "sx",
                "statisticGlobal", "token", "iWantToQuit", "createToken", "nodeServer", "sm"})
public class Node implements Comparable<Node> {

    private int ID;
    private String IP;
    private int listeningPort;

    private String ipGateway;
    private int portGateway;
    private PM10Simulator pm10Sim;
    private Token token;
    private boolean createToken;
    private StatisticManager sm;

    private List<Node> nodeList;
    private Queue<Measurement> statisticGlobal;
    private boolean iWantToQuit;
    private NodeServer nodeServer;

    private Node dx;
    private Node sx;


    public Node() {
        this.nodeList = new ArrayList<>();
        this.statisticGlobal = new LinkedList<>();
        this.iWantToQuit = false;
        this.createToken = false;
    }

    // Getters e Setters
    public String getIpGateway() {
        return this.ipGateway;
    }

    public void setIpGateway(String ipGateway) {
        this.ipGateway = ipGateway;
    }

    public int getPortGateway() {
        return this.portGateway;
    }

    public void setPortGateway(int portGateway) {
        this.portGateway = portGateway;
    }

    public PM10Simulator getPm10Sim() {
        return this.pm10Sim;
    }

    public void setPm10Sim(PM10Simulator pm10Sim) {
        this.pm10Sim = pm10Sim;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIP() {
        return this.IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getListeningPort() {
        return this.listeningPort;
    }

    public void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    public List<Node> getNodeList() { return this.nodeList; }

    public Node getDx() {
        return dx;
    }

    protected void setDx(Node dx) {
        this.dx = dx;
    }

    public Node getSx() {
        return sx;
    }

    protected void setSx(Node sx) {
        this.sx = sx;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean alone() {
        return this.nodeList.size() == 1;
    }

    public boolean isiWantToQuit() {
        return this.iWantToQuit;
    }

    public void quit() {
        this.iWantToQuit = true;
    }

    public void setCreateToken(boolean t) {
        this.createToken = t;
    }

    public boolean getCreateToken() {
        return this.createToken;
    }

    public NodeServer getNodeServer() {
        return nodeServer;
    }

    public void setNodeServer(NodeServer nodeServer) {
        this.nodeServer = nodeServer;
    }

    @Override
    public String toString() {
        String s = "\nID: " + this.ID;
        s += "\nSuccessore: " + this.getDx().getID();
        s += "\nPredecessore: " + this.getSx().getID();
        return s;
    }

    @Override
    public int compareTo(Node node) {
        return this.getID() - node.getID();
    }

    protected synchronized void printList() {
        String s = "\n[";
        for (Node n : this.getNodeList())
            s += " " + n.getID();
        Utils.printGreen(s + " ]\n");
    }


    public synchronized void addNodeToList(Node n) {
        if (this.nodeList.stream().filter(t -> t.getID() == n.getID()).count() == 0) {
            this.nodeList.add(n);
            Collections.sort(this.nodeList);
        }
    }

    public void addStatisticGlobal(Measurement m) {
        statisticGlobal.add(m);
    }

    public boolean statisticGlobalIsEmpty() {
        return statisticGlobal.isEmpty();
    }


    // Richiesta POST per registrarsi al Gateway
    // Synchronized perchè nel frattempo il NodeServer, che è abilitato, potrebbe ricevere delle richieste e
    // voler comunicare con il nodo. Grazie al synchronized deve attendere
    public synchronized boolean registerToGateway() {
        List<Node> listReceived = NodeRestCall.registerToGateway(this);

        if (listReceived == null)
            return false;

        for (Node n : listReceived)
            addNodeToList(n);

        System.out.print("Lista ricevuta: ");
        printList();


        if (alone()) {
            this.setSx(this);
            this.setDx(this);
        }
        else {
            callNeighbors();
        }


        if (alone()) {
            // Setto il campo createTolen = true qui e non dentro il procedente blocco alone() poichè contattando
            // i miei vicini attraverso callNeighbors potrei scoprire di avere dei vicini offline. I
            // In tale circostanza risultere dunque solo. Con tale campo quando qualche altro nodo entrerà nella
            // rete P2P sarò dunque il nodo che creerà il token
            this.createToken = true;
        }

        System.out.print("Inizializzazione: ");
        Utils.printGreen("OK\n");

        System.out.println("\n*********************");
        System.out.println("Stato del nodo iniziale");
        System.out.print("*********************");
        System.out.println(this);
        System.out.println("*********************");

        return true;
    }


    // Setto il mio successore è predecessore e avviso il mio successore e il mio predecessore
    private void callNeighbors() {
        boolean isFound;
        /*
           Provo ad informare il mio successore nel seguente modo:
           - Calcolo la mia posizione all'interno dell'array (myIndex)
           - Calcola la posizione del mio successore (nextIndex)
           - Se i due indici concidono:
                - Vuol dire che ho fatto un giro nell'array e sono da solo
                - Setto come mio successore me stesso ed esco
              Altrimenti:
                - Provo a contattare il mio successore
                - Se risponde:
                    - Setto come mio successore tale nodo ed esco; il nodo contattato mi
                      aggiungerà alla lista e vedrà se ci sono le condizioni per settarmi
                      come nodo predecessore
                - Se non risponde:
                    - Il nodo non esiste più, quindi lo rimuovo e riparto dall'inizio
         */
        do {
            isFound = false;
            int myIndex = findMyIndex();
            int nextIndex = myIndex + 1 <= this.getNodeList().size() - 1 ? myIndex + 1 : 0;

            if (nextIndex == myIndex) {
                isFound = true;
                this.setDx(this);
            }
            else {
                Node nodeToCall = this.getNodeList().get(nextIndex);
                System.out.print("CallNext " + nodeToCall.getID() + ":");
                NotifyResponse.Status response = syncCallNext(nodeToCall.getIP(), nodeToCall.getListeningPort());
                if (response == NotifyResponse.Status.OK) {
                    Utils.printGreen(" OK\n");
                    this.setDx(nodeToCall);
                    isFound = true;
                }
                else {
                    this.getNodeList().remove(nextIndex);
                    Utils.printRed(" UNAVAILABLE\n");
                }
            }
        } while (!isFound);

        
        // Provo ad informare il mio predecessore
        do {
            isFound = false;
            int myIndex = findMyIndex();
            int previousIndex = myIndex - 1 >= 0 ? myIndex - 1 : this.getNodeList().size() - 1;

            if (previousIndex == myIndex) {
                isFound = true;
                this.setSx(this);
            }
            else {
                Node nodeToCall = this.getNodeList().get(previousIndex);
                System.out.print("CallPrevious " + nodeToCall.getID() + ":");
                NotifyResponse.Status response = syncCallPrevious(nodeToCall.getIP(), nodeToCall.getListeningPort());
                if (response == NotifyResponse.Status.OK) {
                    Utils.printGreen(" OK\n");
                    this.setSx(nodeToCall);
                    isFound = true;
                }
                else {
                    Utils.printRed(" UNAVAILABLE\n");
                    this.getNodeList().remove(previousIndex);
                }
            }
        } while (!isFound);
    }

    // Restituisce la mia posizione all'interno della lista
    protected int findMyIndex() {
        int counter = 0;
        for (Node n : this.getNodeList()) {
            if (n.getID() == this.getID()) {
                return counter;
            }
            counter++;
        }
        return -1;
    }


    // Avviso il mio nodo successivo di essere il suo nuovo predecessore
    private NotifyResponse.Status syncCallNext(String ip, int listeningPort) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget(ip + ":" + listeningPort).usePlaintext().build();
        P2PServiceGrpc.P2PServiceBlockingStub stub = P2PServiceGrpc.newBlockingStub(channel);

        NotifyRequest request = NotifyRequest.newBuilder()
                                    .setId(this.ID)
                                    .setListeningPort(this.listeningPort)
                                    .setIp(this.IP)
                                    .build();

        try {
             NotifyResponse response = stub.notifyNext(request);
             channel.shutdown();
             return response.getStatus();
        }
        catch(io.grpc.StatusRuntimeException e) {
            channel.shutdown();
            return null;
        }
    }



    // Avviso il mio nodo precedente di essere il suo nuovo successore
    private NotifyResponse.Status syncCallPrevious(String ip, int listeningPort) {
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(ip + ":" + listeningPort).usePlaintext().build();
        P2PServiceGrpc.P2PServiceBlockingStub stub = P2PServiceGrpc.newBlockingStub(channel);

        NotifyRequest request = NotifyRequest.newBuilder()
                .setId(this.ID)
                .setListeningPort(this.listeningPort)
                .setIp(this.IP)
                .build();

        try {
            NotifyResponse response = stub.notifyPrevious(request);
            channel.shutdown();
            return response.getStatus();
        }
        catch(io.grpc.StatusRuntimeException e) {
            channel.shutdown();
            return null;
        }
    }



    // Avviso il mio nodo successivo di voler uscire e gli passo il mio predecessore nell'header del msg GRPC
    private void deleteNext(String ip, int listeningPort) {
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(ip + ":" + listeningPort).usePlaintext().build();

        P2PServiceGrpc.P2PServiceBlockingStub stub = P2PServiceGrpc.newBlockingStub(channel);

        NotifyRequestDelete request = NotifyRequestDelete.newBuilder()
                .setIdDelete(this.getID())
                .setIdAdd(this.getSx().getID())
                .setIpAdd(this.getSx().getIP())
                .setListeningPortAdd(this.getSx().getListeningPort())
                .build();

        try {
            stub.notifyNextDelete(request);
            channel.shutdown();
        }

        catch(io.grpc.StatusRuntimeException e) {
            channel.shutdown();
        }
    }

    // Avviso il mio nodo precedente di voler uscire e gli passo il mio successore
    private void deletePrevious(String ip, int listeningPort) {
        final ManagedChannel channel =
                ManagedChannelBuilder.forTarget(ip + ":" + listeningPort).usePlaintext().build();

        P2PServiceGrpc.P2PServiceBlockingStub stub = P2PServiceGrpc.newBlockingStub(channel);

        NotifyRequestDelete request = NotifyRequestDelete.newBuilder()
                .setIdDelete(this.getID())
                .setIdAdd(this.getDx().getID())
                .setIpAdd(this.getDx().getIP())
                .setListeningPortAdd(this.getDx().getListeningPort())
                .build();

        try {
            stub.notifyPreviousDelete(request);
            channel.shutdown();
        }

        catch(io.grpc.StatusRuntimeException e) {
            channel.shutdown();
        }
    }


    public void callNeighborsForDelete() {
        if (this.getSx() != this) {
            deletePrevious(this.getSx().getIP(), this.getSx().getListeningPort());
        }

        if (this.getDx() != this) {
            deleteNext(this.getDx().getIP(), this.getDx().getListeningPort());
        }
    }


    // Fa una media delle statistiche contenute nel proprio buffer
    public Statistic obtainStatisticGlobal() {
        int count = 0;
        double value = 1;
        long timestamp = 0;

        // Recupero tutte le misurazioni globali e nel caso ce ne fosse più di 1 faccio una media
        while (statisticGlobal.size() != 0) {
            Measurement m = statisticGlobal.poll();
            value += m.getValue();
            timestamp = m.getTimestamp();
            count += 1;
        }

        if (count == 0) {
            return null;
        }
        else {
            Statistic s = new Statistic();
            s.setTimestamp(timestamp);
            s.setValue(value / count);
            return s;
        }


    }

    public StatisticManager getSm() {
        return sm;
    }

    public void setSm(StatisticManager sm) {
        this.sm = sm;
    }
}