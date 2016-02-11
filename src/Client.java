/**
 * This code created by 
 * Documented by Omar Alamoudi
 * */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client implements Runnable {
    protected Socket socket;
    protected ObjectOutputStream output;
    protected ObjectInputStream input;
    protected String ip;
    protected int port;
    protected Game game;

    /**
     * create the client 
     * @param ip the ip address of the server
     * @param port the port of the server
     * */
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    /**
     * @param password
     * @return true if connenction established
     * @return false if connection error
     * */
    public boolean join(String password) throws Error {
        try {
            try {
            	int serverCode = getInput().readInt();
                setSocket(new Socket(getIp(), getPort()));
                setOutput(new ObjectOutputStream(getSocket().getOutputStream()));
                setInput(new ObjectInputStream(getSocket().getInputStream()));
                getOutput().writeUTF(password);
                getOutput().flush();

                if (Server.connectionInfo.get(serverCode).name().startsWith("error")) {
                    throw new Error(Server.connectionInfo.get(serverCode).name());
                }
                if (serverCode == Server.connectionInfo.allOK.getValue()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Error error) {
            	error.printStackTrace();
            	return false;
            } catch (ConnectException ex) {
            	ex.printStackTrace();
            	return false;
            }
        } catch (UnknownHostException ex) {
        	ex.printStackTrace();
        	return false;
        } catch (IOException ex) {
        	ex.printStackTrace();
        	return false;
        }
    }

	public void run() {
		// TODO Auto-generated method stub
	}
	
    /*
     * Next section are bunch of set and get 
     * */

    /**
     * Get this.game
     * @return game object
     * */
	public Game getGame() {
        return this.game;
    }
    /**
     * Set this.game
     * @param game
     * */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Get socket
     * @return socket
     * */
    public Socket getSocket() {
        return socket;
    }
    
    /**
     * Set socket
     * @param socket
     * */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    /**
     * Get output
     * @return output
     * */
    public ObjectOutputStream getOutput() {
        return this.output;
    }
    
    /**
     * Set output
     * @param output
     * */
    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }
    
    /**
     * Get input
     * @return input
     * */
    public ObjectInputStream getInput() {
        return this.input;
    }
    
    /**
     * Set input
     * @param input 
     * */
    public void setInput(ObjectInputStream input) {
        this.input = input;
    }
    
    /**
     * Get the ip address of the server
     * @return the IP address of the server
     * */
    public String getIp() {
        return ip;
    }
    
    /**
     * Set the IP address of the server
     * @param ip the ip number of the server
     * */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Get the port of the server
     * @return port number
     * */
    public int getPort() {
        return port;
    }

    /**
     * Set the port of the server
     * @param port the number of the port
     * */
    public void setPort(int port) {
        this.port = port;
    }
}
