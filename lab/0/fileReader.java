import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerReadFile {

    public void emptyLine(){
        line = reader.readLine();
            if ("".equals(line)) {
                //this is and empty line...
        }
    }

    public static void main( String[] args ) {

        final File file = new File( "data.txt" );

        try ( final Scanner scanner = new Scanner( file ); ) {
            while ( scanner.hasNextLine() ) {
                String line = scanner.nextLine();
                System.out.println( line );
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }
}
