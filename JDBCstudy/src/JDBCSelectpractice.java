import java.util.Scanner;

public class JDBCSelectpractice {
    public static void main(String[] args) {
//        int x=20,y=5;
//        System.out.println(x+y+""+(x+y)+y);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(toLowerCase(str));
    }

    public static String toLowerCase(String str){
        StringBuffer str_n = new StringBuffer();
        for (int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch>='A'&&ch<='Z')
                ch = (char) (ch -'A'+'a');
            str_n.append(ch);
        }
        return str_n.toString();
    }
}
