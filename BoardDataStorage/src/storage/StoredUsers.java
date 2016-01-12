package storage;

import BoardModules.User;
import Interfaces.IUserStorage;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthäus Piechowiak
 * @version 1.0
 * Klasse zum speichern der Usernamen in einer .txt Datei
 * createUsers() - Erstellt die users.txt Datei
 * pushUsers() - speichert die Usernamen in der .txt Datei
 * checkIfUserExists() - Überprüfung ob User schon in .txt steht
 * stripDuplicatesFromFile() - Löscht doppelte namen au der .txt Datei
 * readUsersFromTxt() - Speichert die User in ein Array
 * 
 * 
 * Der Speicherort muss letztendlich noch in jeder Methode geändert werden
 * Die Variable "user_id" u.U. ebenso.
 * 
 */
public class StoredUsers{

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
     * @param identifier
     */
    /*
    public void createUsers(String identifier)
    {
        try
        {
            user_file = new File(identifier + "users.txt");
            filewriter = new FileWriter(user_file ,true);
            filewriter.flush();
            filewriter.close();
        }
            catch (IOException e) 
            {
//                e.printStackTrace();
            }  
    }
    */
    /**
     * Schreibt die User in bestimmte Dateien, abhängig vom identifier
     * users.txt Datei
     * @param identifier
     * @param user_id (Name der User)
     * @return user_id
     */
    public User pushUsersToBoard(String identifier, String user_id)
    {   
        User return_User = null;
        try
        {       
                user_file = new File(identifier + "users.txt");
                filewriter = new FileWriter(user_file ,true);
                if(user_file.exists())
                {
                                    
                    filewriter.write("\n" + user_id);
                    filewriter.flush();
                    filewriter.close();
                    
                }
                else
                {
                    filewriter.write("Kaputt");
                }

        }
        catch (IOException e) {
//            e.printStackTrace();
        }
         return return_User;
        
    }
    /**
     * Prueft ob bestimmte UserNamen in users.txt bereits vorhanden sind.
     * War ursprünglich für was anderes gedacht, aber könnte trotzdem
     * nützlich sein, deshalb hab ich es drin gelassen.
     * @param identifier
     * @param user_id
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void checkIfUserExists(String identifier, String user_id) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(identifier + "users.txt"));
        String line;
        while ((line = br.readLine()) != null) 
        {
        if (!line.contains(user_id))
            {
                System.out.println("Username existiert schon!");
                break;
            }
        else
            {
                System.out.println("User wurde gespeichert!");
                break;
            }
        }
    }
    
    /**
     * Ruft users.txt auf und prüft auf doppelte Nutzernamen.
     * Da man am Ende keine Datei haben will, in der 50 mal derselbe user
     * drin steht, werden doppelte Usernamen auch gleich gelöscht.
     * @param identifier
     * @throws FileNotFoundException
     * @throws IOException 
     */
public void stripDuplicatesFromFile(String identifier) throws FileNotFoundException, IOException 
{
    Set<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(identifier + "users.txt")))
        {
            lines = new HashSet<>(10000);
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }   
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(identifier + "users.txt"))) 
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
 * Die erste Zeile ist ein newline, Array-Index fängt also bei 1 an.
 * 
     * @param identifier
 * @throws FileNotFoundException
 * @throws IOException 
 */
/*
    public void readUsersFromTxt(String identifier) throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(identifier + "users.txt"));
        String user;
        
        List<String> list = new ArrayList<>();
        while((user = in.readLine()) != null)
        {
            list.add(user);
        }
        
        String[] userNameArray = list.toArray(new String[2]); 
        System.out.println(userNameArray);
    }
    */

    public ArrayList<User> ReadUsersFromTxtFile(String identifier) throws IOException
    {
        User single_user;
        ArrayList<User> user_IDs = new ArrayList<>();
        String zeile;
        
        try
        {
            user_file = new File(identifier + "users.txt");
            if(!user_file.exists())
            {
                System.out.println("Die Tafel exisitiert nicht!");
            }
            else
            {
                FileReader filereader = new FileReader(identifier + "users.txt");
                BufferedReader bufferedreader = new BufferedReader(filereader);
                while((zeile=bufferedreader.readLine()) != null)
                {
                    user_id = "";
                    
                    for (int i=1; i < zeile.length(); i++)
                    {
                        user_id += zeile.charAt(i);
                    }
                    single_user = new User(user_id);
                    user_IDs.add(single_user);
                }
                bufferedreader.close();
            }

        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }
        
        return user_IDs;
    }
}


