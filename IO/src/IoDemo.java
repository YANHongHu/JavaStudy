import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IoDemo {
    /**
      1.可以从文件中读
      2.可以从网络中读（网卡/TCP连接）
      3.可以从内存中读（内存中的一段空间当成输入源）
      4.可以从标准输入读*/
    private static InputStream 获取一个输入流() throws IOException {
        InputStream inputStream;

        /** 从文件中读
        inputStream = new FileInputStream("itest.txt");

          从内存中读
        byte[] bytes = "我现在是内存中的一段空间\r\n我又是第二行\r\n".getBytes("UTF-8");
        inputStream = new ByteArrayInputStream(bytes);

          从标准输入读
          inputStream = System.in;
        */

        // 从网络中读
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os, "UTF-8");
        PrintWriter printWriter = new PrintWriter(writer,false);
        printWriter.print("GET / HTTP/1.0\r\n\r\n");
        printWriter.flush();

        inputStream = socket.getInputStream();

        return inputStream;
    }
    /**
    1.直接通过字节方式读，对程序进行字符编码
    2.把Stream转化为Reader，进行字符形式读取
        2.1 直接读
        2.2 BufferedReader -> readline
    3.Scanner
     */

    private static String 从字节流中最终获得字符数据(InputStream is) throws IOException {
       /** 1.直接通过字节读
        byte[] buffer = new byte[1024];
        int len = is.read(buffer);

        String message = new String(buffer, 0,len, "UTF-8");
        */

       /** 2.1 把Stream转化为Reader，直接读
        StringBuilder sb = new StringBuilder();
        Reader reader = new InputStreamReader(is,"UTF-8");
        char[] buffer = new char[10];
        int len = reader.read(buffer);
        while(len!=-1){
            sb.append(buffer, 0,len);
        }
        String message = sb.toString();
        */

        /** 2.2 把Stream转化为Reader，用BufferReader
        Reader reader = new InputStreamReader(is, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while(line!=null){
            sb.append(line+"\r\n");
        }
        String message = sb.toString();
         */

        // scanner
        Scanner scanner = new Scanner(is);
        return scanner.next();
    }



    private static OutputStream 获取一个输出流() throws FileNotFoundException {
        OutputStream os = new FileOutputStream("otext.txt");

        /**
        Socket socket = new Socket("www.baidu.com", 80);
        OutputStream os = socket.getOutputStream();
        */


        //OutputStream os = new ByteArrayOutputStream();

        return os;

    }

    private static void 输出一段字符(OutputStream os, String message) throws UnsupportedEncodingException {
        /*
        byte[] buffer = message.getBytes("UTF-8");
        os.write(buffer);
         */

        Writer writer = new OutputStreamWriter(os, "UTF-8");
        //writer.append(message);
        //writer.flush();

        PrintWriter printWriter = new PrintWriter(writer, false);
        printWriter.printf("%s", message);
        printWriter.flush();

    }


    public static void main(String[] args) throws IOException {
         String result = 从字节流中最终获得字符数据(获取一个输入流());
         System.out.print(result);

         OutputStream os = 获取一个输出流();
         输出一段字符(os, "我是中国人，最喜欢过年了\r\n");
    }
}


