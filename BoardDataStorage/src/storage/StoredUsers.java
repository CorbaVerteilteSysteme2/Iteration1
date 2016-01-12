package storage;

import BoardModules.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Matthäus Piechowiak
 * @version 1.0
 * Klasse zum speichern der Usernamen in einer .txt Datei
 * 
 * Der Speicherort muss letztendlich noch in jeder Methode geändert werden
 * Die Variable "user_id" u.U. ebenso.
 * 
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
    /**
     * Erstellt eine "users.txt" Datei
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
    /**
     * Bekommt einen String und schreibt diesen in die
     * users.txt Datei
     * @param user_id (Name der User)
     * @return user_id
     */
    public String pushUsers(String user_id)
    {   
        try
        {
            user_file = new File("users.txt");
            /*
            if(!user_file.exists())
            {
                System.out.println("Test, existiert nicht");
            }
            
            else
            {
            */
                filewriter = new FileWriter(user_file ,true);
                filewriter.write("\n" + user_id);
                filewriter.flush();
                filewriter.close();
            //}
        }
        catch (IOException e) {
//            e.printStackTrace();
        }
         return user_id;
        
    }
    /**
     * Prueft ob bestimmte UserNamen in users.txt bereits vorhanden sind.
     * War ursprünglich für was anderes gedacht, aber könnte trotzdem
     * nützlich sein, deshalb hab ich es drin gelassen.
     * @param user_id
     * @throws FileNotFoundException
     * @throws IOException 
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
    
    /**
     * Ruft users.txt auf und prüft auf doppelte Nutzernamen.
     * Da man am Ende keine Datei haben will, in der 50 mal derselbe user
     * drin steht, werden doppelte Usernamen auch gleich gelöscht.
     * @throws FileNotFoundException
     * @throws IOException 
     */
public void stripDuplicatesFromFile() throws FileNotFoundException, IOException 
{
    Set<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            lines = new HashSet<>(10000);
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }   
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) 
        {
            for (String unique : lines)
            {
                writer.write(unique);
                writer.newLine();
            }   
        }
}

/**
 * Methode liest die einzelnen User aus der .txt Datei in ein Array.
 * 
 * @throws FileNotFoundException
 * @throws IOException 
 */
    public void readUsersFromTxt() throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("users.txt"));
        String user;
        
        List<String> list = new ArrayList<>();
        while((user = in.readLine()) != null)
        {
            list.add(user);
        }
        
        String[] userNameArray = list.toArray(new String[0]);
        
        /*
        Notiz: Zurzeit ist die erste Zeile nur ein newline
        Deshalb fängt das Array hier bei 1 an.
        */
        System.out.println(userNameArray[1]);
        
    }
    /**
     * Main Methode, zum reinen Testzweck dieser Klasse
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        StoredUsers test = new StoredUsers();
        test.createUsers();
        test.pushUsers("MAX");
        //test.checkIfUserExists("Max");
        test.stripDuplicatesFromFile();
        test.readUsersFromTxt();
    }
}


