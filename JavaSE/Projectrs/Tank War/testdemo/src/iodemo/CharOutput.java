package iodemo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharOutput {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("testdemo/src/iodemo/test1",true);
        Scanner sc = new Scanner(System.in);
        fw.write(97);

        fw.write("hello world");
        String str = sc.nextLine();
        char [] ch = new char[10];
        fw.write(str,0,5);
//        fw.write(ch);
        fw.close();
    }


}
