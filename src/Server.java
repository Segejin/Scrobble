/**
 * This code was created by
 * Documented by Omar Alamoudi
 * */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server implements Runnable {
    public static int port = 4449;
    private static ServerSocket serverSocket;
    private static boolean isRunning = false;
    
    public static enum connectionInfo {
        allOK(0),
        errorGameFull(1),
        errorBadPassword(2);
    	
    	private int value;

        connectionInfo (int value) {
            this.value = value;
        }
        
        public static connectionInfo get(int id) {
            switch(id) {
                case 0:
                    return connectionInfo.allOK;
                case 1:
                    return connectionInfo.errorGameFull;
                case 2:
                    return connectionInfo.errorBadPassword;
                default:
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * Server initiating 
     * */
    public Server() {
        if(!Server.isRunning) {
            runServer();

            Thread thread = new Thread(this);
            thread.start();

            Server.isRunning = true;
        }
    }
    
    /**
     * Check if the server is runnung
     * */
    public static boolean isRunning() {
        return isRunning;
    }

    /**
     * Run the server
     * */
    private static void runServer() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
    /**
     * Run the server 
     * */
    public void run() {
    	while(true) {
    		Socket socket;
    		ObjectInputStream input;
    		ObjectOutputStream output;
    		
    		try {
    			socket = serverSocket.accept();
    			input = new ObjectInputStream(socket.getInputStream());
    			output = new ObjectOutputStream(socket.getOutputStream());
    		} catch (IOException ex) {
    			ex.printStackTrace();
    			continue;
            }
    	}
    }
    
    /**
     * CLient class
     * */
    private class Client implements Runnable {
        private Socket socket;
        public ObjectInputStream input;
        public ObjectOutputStream output;
        
        /**
         * Establish connection with the server
         * */
        Client(Socket socket, ObjectInputStream input, ObjectOutputStream output){
            this.socket = socket;
            this.input = input;
            this.output = output;

            Thread thread = new Thread(this);
            thread.start();
        }

        /**
         * run the client
         * */
        public void run() {
            while(true) {
                try {
                    String in = input.readUTF();
                } catch (IOException ex) {
                	ex.printStackTrace();
                }
            }
        }
    }
}
