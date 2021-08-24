/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlsv13server;

/**
 *
 * @author Ronnie
 */
public class TLSv13Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here   
        System.setProperty("javax.net.ssl.keyStore","C:\\Users\\Ronnie\\Documents\\NetBeansProjects\\TLSv13Server\\keystore" );
        System.setProperty("javax.net.ssl.keyStorePassword","passphrase" );
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\Ronnie\\Documents\\NetBeansProjects\\TLSv13Server\\truststore" );
        System.setProperty("javax.net.ssl.trustStorePassword","passphrase" );
        System.setProperty("javax.net.ssl.trustStorePassword","passphrase" );
        System.setProperty("javax.net.ssl.keyStoreType","PKCS12" );        
        TLSv13ServerThread tls13 = new TLSv13ServerThread(8888);
        Thread thread = new Thread(tls13);
        thread.start();
        
    }
    
}
