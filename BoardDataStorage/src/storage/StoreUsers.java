package storage;

import BoardModules.User;
import Interfaces.IUserStorage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matthäus Piechowiak
 */
public class StoreUsers implements IUserStorage{
    
    User _name;
    String user_id;
    String identifier;
    File user_file;
    FileWriter filewriter;
 
    StoreUsers(){}
    
    StoreUsers(String identifier, String user_id)
        {
            this.identifier = identifier;
            this.user_id = user_id;
        }
    
    /**
     * Schreibt eine bestimmte Tafel und fügt Benutzer hinzu
     * @param identifier
     * @param user 
     */
    @Override
    public void storeUser(String identifier, User user) 
    {   
        try
        {       
            File user_file = new File(identifier + "users.txt");
            FileWriter filewriter = new FileWriter(user_file ,true);

                    filewriter.write("\n" + user);
                    filewriter.flush();
                    filewriter.close();

        }
        catch (IOException e) {
        // e.printStackTrace();
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Lädt Usernamen aus bestimmten Tafeln.
     * @param identifier
     * @return 
     */
    @Override
    public ArrayList<User> loadAllUsers(String identifier) 
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
                    
                    for (int i=0; i < zeile.length(); i++)
                    {
                        user_id += zeile.charAt(i);
                    }
                    single_user = new User(user_id);
                    user_IDs.add(single_user);
                }
                bufferedreader.close();
            }
            System.out.println(user_IDs);
            return user_IDs;
        }
        
        catch (IOException e)
        {
            //e.printStackTrace();
        }
              
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(String identifier, User user) {
        System.out.println("Wird noch implementiert");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
