package iodemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharInput {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("testdemo/src/iodemo/test2");
//        int c;
//       while((c = fr.read())!=-1)
//       {
//           System.out.print((char)c);
//       }
        int ch;
        char [] buf =new char[23];
        while ((ch = fr.read(buf)) != -1) { // 读入数据由char数组存储 返回字符数 （字节流就是字节数）
            System.out.println(new String(buf,0,ch)); // 解码
            System.out.println(ch);
        }


            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

