import java.io.*;
import java.net.*;
import java.util.LinkedList;


public class ServerTest {

    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента
    
    /**
     * @param args
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}

class ServerSomthing extends Thread {
    
    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток завписи в сокет
    
    /**
     * для общения с клиентом необходим сокет (адресные данные)
     * @param socket
     * @throws IOException
     */
    
    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        // если потоки ввода/вывода приведут к генерированию искдючения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start(); // вызываем run()
    }

    @Override
    public void run() {
        String data;
        try {
            // первое сообщение отправленное сюда - это никнейм
            data = in.readLine();
            try {
                out.write(data + "\n");
                out.flush(); // flush() нужен для выталкивания оставшихся данных
                // если такие есть, и очистки потока для дальнейших нужд
            } catch (IOException ignored) {}
            try {
                while (true) {
                    data = in.readLine();
                    if(data.equals("stop")) {
                        this.downService(); // харакири
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    System.out.println(serverList.size() + " users: " + data);
                }
            } catch (NullPointerException ignored) {}

            
        } catch (IOException e) {
            this.downService();
        }
    }
    
    /**
     * отсылка одного сообщения клиенту по указанному потоку
     * @param msg
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}   
    }
    
    /**
     * закрытие сервера
     * прерывание себя как нити и удаление из списка нитей
     */
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
