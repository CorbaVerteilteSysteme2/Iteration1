package UserStorage;

import BoardConfiguration.BoardConfiguration;
import BoardModules.User;
import Interfaces.IUserStorage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matthäus Piechowiak
 * 
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
            String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier
                    + BoardConfiguration.USER_STORAGE_PATH;
            try 
            (XMLEncoder enc = new XMLEncoder(new FileOutputStream(datname,true))) 
            {
                enc.writeObject(user);
            } 
            catch (IOException e) 
            {
                System.out.println("Fehler: Konnte nicht gespeichert werden!");
                e.printStackTrace();
            }
	}
    /**
     * Lädt Usernamen aus bestimmten Tafeln.
     * @param identifier
     * @return 
     */
    	@Override
	public ArrayList<User> loadAllUsers(String identifier) 
        {
            String datname = BoardConfiguration.COMMON_STORAGE_PATH + identifier
                + BoardConfiguration.USER_STORAGE_PATH;
            XMLDecoder dec = null;
            ArrayList<User> userList = new ArrayList<>();
            User benutzer = null;
            
                try 
                {
                dec = new XMLDecoder(new FileInputStream(datname));
                benutzer = (User)dec.readObject();
                } 
                catch (IOException e) 
                {
                        System.out.println("Fehler: Konnte nicht laden!");
                        e.printStackTrace();
                } 
                finally 
                {
                    if(dec != null) 
                    {
                        dec.close();
                    }
                }
            userList.add(benutzer);
            return userList;
	}

    @Override
    public void removeUser(String identifier, User user) {
        System.out.println("Wird noch implementiert");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
