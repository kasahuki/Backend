package iodemo;

import java.io.*;
import java.util.Scanner;

public class BufferedCharStream {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new FileReader("testdemo/src/iodemo/test1"));
       BufferedWriter bw = new BufferedWriter(new FileWriter("testdemo/src/iodemo/test2",true));
       bw.write("hello");
       Scanner sc = new Scanner(System.in);
       String str = sc.nextLine();
        bw.newLine();

       bw.write(str);


       bw.close();



    }
}
