package it.maurosaladino.sdp.Builder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;

import it.maurosaladino.sdp.Model.Analyst;
import it.maurosaladino.sdp.Model.AvgStatistic;
import it.maurosaladino.sdp.Model.Statistic;
import it.maurosaladino.sdp.Model.Utils;
import it.maurosaladino.sdp.Gateway.Configuration;
import it.maurosaladino.sdp.RestCall.AnalystRestCall;

public class AnalystBuilder {

    private BufferedReader br;
    private final String msgID = "Identificatore: ";
    private final String msgListeningPort = "Listening port dell'analista: ";
    private final String msgIP = "Ottengo l'indirizzo IP dell'analista:";
    private final String msgIPGateway = "Ottengo l'indirizzo IP del Gateway:";
    private final String msgPortGateway = "Ottengo la porta del Gateway:";

    private Analyst analyst;

    public AnalystBuilder(Analyst a) {
        this.analyst = a;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }


    public int getInputID() throws IOException {
        System.out.print(msgID);

        do {
            String value = br.readLine();

            if (value.equals("quit"))
                System.exit(0);

            else if (value.matches("[1-9]+[0-9]*"))
                return Integer.parseInt(value);

            else
                System.out.print(msgID);
        }
        while (true);
    }


    public String getInputIP() {
        System.out.print(msgIP);
        try
        {
            String ip = Inet4Address.getLocalHost().getHostAddress();
            Utils.printGreen(" OK\n");
            return ip;
        }
        catch (UnknownHostException e) {
            Utils.printRed("Non riesco a recuperare l'IP del nodo");
            System.exit(-1);
        }
        return null;
    }


    public int getInputListeningPort() throws IOException {
        System.out.print(msgListeningPort);

        do {
            String value = br.readLine();

            if (value.equals("quit"))
                System.exit(0);

            else if (value.matches("[0-9]+") && Integer.parseInt(value) >= 0 && Integer.parseInt(value) <= 65535)
                return Integer.parseInt(value);

            else
                System.out.print(msgListeningPort);
        }
        while (true);
    }


    public String getIPGateway() {
        System.out.print(msgIPGateway);
        String ipGateway = Configuration.GatewayIP;
        Utils.printGreen(" OK\n");
        return ipGateway;
    }


    public int getPortGateway() {
        System.out.print(msgPortGateway);
        int portGateway = Configuration.getPortGateway();
        Utils.printGreen(" OK\n");
        return portGateway;
    }


    public void enterMenu() throws IOException {
        do {
            menu();
            String value = this.br.readLine();
            switch (value) {
                case "1":
                    getSizeNodesList();
                    break;

                case "2":
                    getLastStatistics();
                    break;

                case "3":
                    getAvgStatistics();
                    break;

                case "4":
                    AnalystRestCall.sendRemove(analyst.getID());
                    analyst.getServer().shutdownServer();
                    System.exit(1);

                default:
                    System.out.print("Selezionare un opzione [1-4]: ");

            }
        } while (true);
    }


    private void getSizeNodesList() {
        int sizeNodeList = AnalystRestCall.getSizeNodesList();

        if (sizeNodeList >= 0) {
            System.out.println("--------------------------------------\n");
            System.out.print("Numero di nodi presenti nella rete: ");
            Utils.printGreen(sizeNodeList + "\n");
            System.out.println("\n--------------------------------------");
        }

        else {
            Utils.printRed("Risposta del server sconosciuta");
            System.exit(-1);
        }
    }


    private void getLastStatistics() throws IOException {
        String n;
        do {
            System.out.print("Inserire n: ");
            n = br.readLine();
        } while (!n.matches("[1-9]+[0-9]*"));

        Statistic[] lastStatistic = AnalystRestCall.getLastStatistics(n);

        if (lastStatistic.length > 0) {
            System.out.println("--------------------------------------\n");
            Utils.printGreen("Elenco delle ultime " + n + " statistiche" + "\n");
            System.out.println("\n--------------------------------------");

            for (Statistic s: Arrays.asList(lastStatistic))
                System.out.println(s);
        }

        else if (lastStatistic.length == 0) {
            Utils.printRed("La rete P2P non ha prodotto ancora alcuna statistica");
        }

        else {
            Utils.printRed("Risposta del server sconosciuta");
            System.exit(-1);
        }
    }


    private void getAvgStatistics() throws IOException {
        String n;
        do {
            System.out.print("Inserire n: ");
            n = br.readLine();
        } while (!n.matches("[1-9]+[0-9]*"));

        AvgStatistic a = AnalystRestCall.getAvgStatistics(n);

        if (a.getAvg() > 0) {
            System.out.println("---------------------------------------------------------\n");
            Utils.printGreen("Deviazione standard e avg delle ultime " + n + " statistiche" + "\n");
            System.out.println("\n---------------------------------------------------------");
            System.out.println(a);
        }

        else if (a.getAvg() == -1) {
            Utils.printRed("La rete P2P non ha prodotto ancora alcuna statistica");
        }

        else {
            Utils.printRed("Risposta del server sconosciuta");
            System.exit(-1);
        }
    }


    public static void instructions() {
        System.out.println("############################");
        System.out.println("#        ISTRUZIONI        #");
        System.out.println("############################\n");
        System.out.println("- Per terminare il setup digitare in qualsiasi momento quit");
        System.out.println("- Come identificatore dell'analista sono ammessi solo numeri interi maggiori di 0 (zero)");
        System.out.println("- La listening port dell'analista può essere compresa, salvo disponibilità, nel seguente intervallo: [0-65535]\n\n");
    }

    public static void setup() {
        System.out.println("############################");
        System.out.println("#           SETUP          #");
        System.out.println("############################\n");
    }

    public static void menu() {
        System.out.println("\n############################");
        System.out.println("#           MENU           #");
        System.out.println("############################\n");
        System.out.println("1. Numero di nodi presente nella rete");
        System.out.println("2. Ultime n statistiche (con timestamp) di quartiere");
        System.out.println("3. Deviazione standard e media delle ultime n statistiche prodotte dal quartiere");
        System.out.println("4. Exit\n");
        System.out.print("Selezionare un opzione [1-4]: ");
    }
}
