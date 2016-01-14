package UserStorage;

import BoardConfiguration.BoardConfiguration;
import BoardModules.User;
import Interfaces.IUserStorage;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Matthäus Piechowiak
 * übernnomen aus StoreMessage
 * 
 */
public class StoreUsers implements IUserStorage
{
    @Override
    public void storeUser(String identifier, User user)
    {
        String file_name = BoardConfiguration.COMMON_STORAGE_PATH + identifier
                + BoardConfiguration.USER_STORAGE_PATH;
        XMLEncoder enc = null;
        
        try
        {
            enc = new XMLEncoder(new FileOutputStream(file_name, true));
            ArrayList<User> usrs = this.loadAllUsers(identifier);
            usrs.add(user);
            enc.writeObject(usrs);          
        }
        catch (IOException e)
        {
            System.out.println("Fehler: User konnte nicht gespeichert werden");
            e.printStackTrace();
        }
        finally
        {
            if(enc != null)
            {
                enc.close();
            }
        }
        
    }

    @Override
    public ArrayList<User> loadAllUsers(String Identifier) 
    {
        String file_name = BoardConfiguration.COMMON_STORAGE_PATH + Identifier
                + BoardConfiguration.USER_STORAGE_PATH;
        XMLDecoder dec = null;
        ArrayList<User> usr_list = null;
        
        try
        {
            dec = new XMLDecoder(new FileInputStream(file_name));
            usr_list = (ArrayList<User>) dec.readObject();
        }
        catch (FileNotFoundException ex)
        {
            usr_list = new ArrayList<>();
        }
        
        return usr_list;
    }

    @Override
    public void removeUser(String identifier, User user) 
    {
        String file_name = BoardConfiguration.COMMON_STORAGE_PATH + identifier
                + BoardConfiguration.USER_STORAGE_PATH;
        ArrayList<User> usr_list = null;
        
        usr_list = this.loadAllUsers(identifier);
        for(User benutzer : usr_list)
        {
            if(benutzer.equals(user))
            {
                usr_list.remove(benutzer);
            }
        }
        this.storeUserList(identifier, usr_list);
        
    }

    public void storeUserList(String identifier, ArrayList<User> usr_list) 
    {
        String file_name = BoardConfiguration.COMMON_STORAGE_PATH + identifier
                + BoardConfiguration.USER_STORAGE_PATH;
        XMLEncoder enc = null;
        
        try
        {
            enc = new XMLEncoder(new FileOutputStream(file_name, true));
            enc.writeObject(usr_list);
        }
        catch (IOException e)
        {
            System.out.println("Fehler: Liste konnte nicht gespeichert werden");
            e.printStackTrace();
        }
        finally
        {
            if(enc != null)
            {
                enc.close();
            }
        }
        
    }
    
}
