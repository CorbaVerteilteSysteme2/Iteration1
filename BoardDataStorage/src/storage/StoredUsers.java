package storage;

/**
 * Wegen des Aufbaus der MessageParser Klasse, liest diese Klasse
 * den ersten Token aus der erstellten .txt raus, welcher
 * der Username sein soll, und gibt ihn aus.
 * Alleine wie man hier schon mit Objekten hantieren muss
 * um etwas einfaches rauszubekommen, gibt mir aber auch das Gefühl dass
 * die Idee mit der .txt Datei vielleicht nicht das optimale ist.
 * 
 * Höchstwahrscheinlich bin ich aber auch auf nem ganz falschen Pfad,
 * da ich mit mit Java, regulären Ausrücken, Substrings, etc eigentlich
 * auch nich wirklich auskenne
 * 
 * Am sinnvollsten wäre es, die Usernamen direkt bei der
 * Eingabe aus der GUI zu ziehen (Wozu man aber eine art DB bräuchte, 
 * wobei dann aber ein extra Service laufen muss)
 * Gilt auch für die gesendeten Nachrichten
 * 
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Matthäus Piechowiak
 */
public class StoredUsers {
  
    private String readFile() throws IOException
    {
        File file = new File("Group_id.txt");
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeperator = System.getProperty("MussNenStringEinfuegen");
        String username = scanner.nextLine();
        try
        {
            while(scanner.hasNextLine())
            {
                fileContents.append(scanner.nextLine()).append(lineSeperator);
                String output = username.substring(0, username.indexOf(":"));
                System.out.println(output);
            }
            //Als reines Beispiel
            return fileContents.toString();
        }
        
        finally
        {
            scanner.close();
        }        
    }
    //Nur ein Test
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        StoredUsers test = new StoredUsers();
        test.readFile();
    }
}



