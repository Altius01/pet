import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server {
    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
    
class ServerSomthing extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        
    }
    
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}   
    }
    
    private void downService() {
            try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerSomthing vr : ServerTest.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    ServerTest.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
    }
}
