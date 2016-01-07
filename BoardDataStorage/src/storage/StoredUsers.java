package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Matthäus Piechowiak
 */
public class StoredUsers {

    static String user_id;
    File user_file;
    FileWriter filewriter;
    
    StoredUsers(){}
    
    StoredUsers(String user_id)
        {
            this.user_id = user_id;
        }
    /*
    erstellt ein users.txt
    */
    public void createUsers()
    {
        try
        {
            user_file = new File("users.txt");
            filewriter = new FileWriter(user_file ,true);
            filewriter.flush();
            filewriter.close();
        }
            catch (IOException e) 
            {
//                e.printStackTrace();
            }  
    }
    /*
    Nimmt nen user_id String und schreibt ihn, in die .txt Datei.
    */
    public String pushUsers(String user_id)
    {   
        try
        {
            user_file = new File("users.txt");
            if(!user_file.exists())
            {
                System.out.println("Test, existiert nicht");
            }
            
            else
            {
                filewriter = new FileWriter(user_file ,true);
                filewriter.write("\n" + user_id);
                filewriter.flush();
                filewriter.close();
            }
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
         return user_id;
        
    }
    /*
    Braucht man nicht unbedingt, ich lass es trotzdem drin
    */
    public void checkIfUserExists(String user_id) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = br.readLine()) != null) 
        {
        if (line.contains(user_id))
            {
                System.out.println("Username existiert schon!");
            }
        else
            {
                System.out.println("User wurde gespeichert!");
            }
        }
    }
    
    /*
    löscht doppelte usernamen aus der Liste raus
    */
public void stripDuplicatesFromFile() throws FileNotFoundException, IOException 
{
    Set<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            lines = new HashSet<>(10000); // maybe should be bigger
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }   } // maybe should be bigger
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) 
        {
            for (String unique : lines)
            {
                writer.write(unique);
                writer.newLine();
            }   
        }
}

    //Nur zum testen
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        StoredUsers test = new StoredUsers();
        test.createUsers();
        test.pushUsers("Max"); 
        test.pushUsers("Maxi");  
        test.pushUsers("Mexiko");
        //test.checkIfUserExists("Max");
        test.stripDuplicatesFromFile();
    }
}


