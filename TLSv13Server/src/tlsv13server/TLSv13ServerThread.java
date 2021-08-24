/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlsv13server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Ronnie
 */
public class TLSv13ServerThread implements Runnable {

    
    private SSLServerSocket serverSocket = null;
    private int port;
    private final String[] protocols = new String[] {"TLSv1.3"};
    private final String[] cipher_suites = new String[] {"TLS_AES_128_GCM_SHA256"};

    public TLSv13ServerThread(int port)
    {
        try {
            this.port = port;
            SSLServerSocket socket = (SSLServerSocket)
                    SSLServerSocketFactory.getDefault().createServerSocket(port);
            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites); 
            socket.setNeedClientAuth(true);
            serverSocket.setReuseAddress(true);
        } catch (IOException ex) {
            Logger.getLogger(TLSv13ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }        
    } 
    
    @Override
    public void run() {
        while (true) {
            try {
                // socket object to receive incoming client
                // requests
                SSLSocket client = (SSLSocket) serverSocket.accept();
                
                // Displaying that new client is connected
                // to server
                System.out.println("New client connected: "
                        + client.getInetAddress()
                                .getHostAddress());
                
                System.out.println(client.getHandshakeApplicationProtocol());
                
                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);
                
                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            } catch (IOException ex) {
                Logger.getLogger(TLSv13ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable {

    private final Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {

                // writing the received message from
                // client
                System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                out.println(line);
            }
        } catch (IOException e) {
            Logger.getLogger(TLSv13ServerThread.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                 Logger.getLogger(TLSv13ServerThread.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
