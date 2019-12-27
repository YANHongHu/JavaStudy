import java.io.*;
import java.net.Socket;

public class tcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        InputStream is =  socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        Reader reader = new InputStreamReader(is, "UTF-8");
        Writer writer  = new OutputStreamWriter(os, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        PrintWriter printWriter = new PrintWriter(writer, false);

        String[] messages = {"早啊", "赶紧吃饭", "赶紧学习", "不要松懈"};
        for (String message : messages) {
            printWriter.println(message);
            printWriter.flush();

            String echoMessage = bufferedReader.readLine();
            System.out.println("对方有回复了: " + echoMessage);
        }

        socket.close();
    }
}
