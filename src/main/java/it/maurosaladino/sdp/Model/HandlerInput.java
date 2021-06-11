package it.maurosaladino.sdp.Model;

import it.maurosaladino.sdp.RestCall.NodeRestCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HandlerInput extends Thread {

    private Node node;

    public HandlerInput(Node n) {
        this.node = n;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = new String();
        boolean quit = false;

        while (!quit) {
            try {
                value = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (value.equals("quit")) {
                quit = true;
                synchronized (node) {
                    if (node.alone()) {
                        node.quit();
                        Utils.printRed("Uscita in corso...");
                        node.getNodeServer().shutdownServer();
                        NodeRestCall.sendRemoveReq(node.getID());
                    }
                    else {
                        node.quit();
                        Utils.printRed("Uscita in corso...");
                    }
                }
            }
        }
    }
}
