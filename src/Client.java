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
    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

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
	
	public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void setOutput(ObjectOutputStream output) {
        this.output = output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}