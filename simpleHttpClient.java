import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class simpleHttpClient {
    public static void main(String[] args) throws IOException {
        String request = "GET / HTTP/1.0\r\nhost: www.baidu.com\r\n\r\n";

        Socket socket = new Socket("www.baidu.com", 80);
        socket.getOutputStream().write(request.getBytes("UTF-8"));
        // 版本   状态码     状态描述
        // 响应头打印
        // 把响应正文保存下来
        byte[] bytes = new byte[14615];
        int len = socket.getInputStream().read(bytes); // 实际读到的数据长度（实际读到的长度<4096）
        System.out.println(len);
        // 假设 4096 字节已经包含 响应行 + 所有响应头 + 一部分正文
        int index = -1;
        for (int i = 0; i < len - 3; i++) {
            if (bytes[i] == '\r' && bytes[i+1] == '\n' && bytes[i+2] == '\r' && bytes[i+3] == '\n') {
                index = i;
                break;
            }
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, index + 4);
        Scanner scanner = new Scanner(byteArrayInputStream, "UTF-8");
        String statusLine = scanner.nextLine();
        System.out.println("状态行: ");
        String[] arr = statusLine.split(" ");
        System.out.println("响应版本: "+arr[0]);
        System.out.println("状态码: "+arr[1]);
        System.out.println("状态描述: "+arr[2]);
        String line;
        System.out.println("响应头: ");
        int content_len = 0;
        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] kv = line.split(":");
            String key = kv[0].trim();
            String value = kv[1].trim();
            if(key.equalsIgnoreCase("content-length")){
                content_len = Integer.valueOf(value); // 把String转为Integer
            }
            System.out.println(key + " = " + value);
        }

        int readed = len - index - 4; // 已经读取正文的长度
        System.out.println("已经读了: "+readed);
        int need_read = content_len - readed;
        System.out.println("还需要读: "+ need_read);
        byte[] body = new byte[content_len];
        System.arraycopy(bytes, index+4, body,0, readed);
        int need_read_len = socket.getInputStream().read(body,readed,need_read);
        System.out.println(need_read_len);
        FileOutputStream fileOutputStream = new FileOutputStream("百度.html");
        fileOutputStream.write(body);
//        String response = new String(bytes, 0,len,"UTF-8");
//        System.out.print(response);
    }
}
