import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class readByLine {
    public readByLine() throws FileNotFoundException{
        Scanner linReader = new Scanner(new File("file.txt"));

        while (linReader.hasNext()){
            String line = linReader.nextLine();
            System.out.println(line.length());
        }

		linReader.close();

    }

    public static void main(String args[])  throws FileNotFoundException{
        new readByLine();
    }
}
