/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlsv13client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Ronnie
 */
public class TLSv13Client {

    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore","C:\\Users\\Ronnie\\Documents\\NetBeansProjects\\TLSv13Server\\keystore1" );
        System.setProperty("javax.net.ssl.keyStorePassword","passphrase" );
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\Ronnie\\Documents\\NetBeansProjects\\TLSv13Server\\truststore" );
        System.setProperty("javax.net.ssl.trustStorePassword","passphrase" );
        System.setProperty("javax.net.ssl.keyStoreType","PKCS12" ); 
        try {
            // TODO code application logic here
            SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault()
                    .createSocket("localhost", 8888);
            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);
            socket.setNeedClientAuth(true);

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                // reading from user
                line = sc.nextLine();

                // sending the user input to server
                out.println(line);
                out.flush();

                // displaying server reply
                System.out.println("Server replied "
                        + in.readLine());
            }

            // closing the scanner object
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
