/*
 *
 */
package BoardCore;

import BoardModules.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * Der BoardCore ist das zentrale Element einer Tafel.
 * Hier werden: 
 *      - der Zugriff auf den ORB realisiert
 *      - auf den Namensdienst zugegriffen
 *      - die Services gestartet
 *      - ...
 * Der BoardCore ist kein Thread.
 * @author Tobias Müller
 */
public class BoardCore extends AbstractCore {
    
    private final ArrayList<User> users;
    
    /**
     * Konstruktor
     * 
     * @param boardID Tafel-Identifier
     * @throws org.omg.CosNaming.NamingContextPackage.CannotProceed
     * @throws org.omg.CosNaming.NamingContextPackage.InvalidName
     */
    public BoardCore(String boardID) throws RuntimeException, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        super(boardID); 
        this.users = new ArrayList<>();
        this.users.add(new User("user1"));
    }
    
    @Override
    public synchronized void addUser(User user) {
        users.add(user);
    }
    
    @Override
    public synchronized boolean checkUser(User user) {
        return users.contains(user);
    }
    
    @Override
    public User[] getAllUsers() {
        User userList[] = new User[users.size()];
        
        for (int i = 0; i < users.size(); i++) {
            userList[i] = users.get(i);
        }
        
        return userList;
    }
    
    @Override
    public void closeCore() {
        super.closeCore();

        NameComponent boardNC = new NameComponent(_identifier, "");
        try {
            ORBAccessControl.getInstance().getNameService().unbind(new NameComponent[] { boardNC });
        } catch (NotFound ex) {
            Logger.getLogger(BoardCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(BoardCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidName ex) {
            Logger.getLogger(BoardCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
