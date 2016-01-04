/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardCore.MessageStorage;
import BoardCore.UserStorage;
import BoardModules.BasicServices.BoardServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.UnknownUser;
import BoardModules.User;

/**
 *
 * @author Tobias
 */
public class BoardServiceImpl extends BoardServicePOA {
    
    /**
     * 
     * @param user
     * @param message
     * @param destination
     * @throws DestinationUnreachable
     * @throws UnknownUser 
     */
    @Override
    public void sendMessage(User user, Message message, String destination) throws DestinationUnreachable, UnknownUser {
        // Benutzer überprüfen
        
        // Ziel unterscheiden zwischen lokale Tafel oder VirtualBoardService
        // destination == "" --> Ziel ist die lokale Tafel! 
        if (destination.equals("")) {
            MessageStorage.getInstance().addMessage(message);
        } else {
            // hier wird an eine virtuelle Gruppe gesendet
        }
    }

    /**
     * 
     * @return 
     */
    @Override
    public String[] getAllVirtualGroups() {
        // TODO
        System.out.println("Testausgabe auf dem Server!");
        return null;
    }

    /**
     * 
     * @param user
     * @throws UnknownUser 
     */
    @Override
    public void checkUser(User user) throws UnknownUser {
        if (!UserStorage.getInstance().checkUser(user)) {
            throw new UnknownUser(user.name);
        }
    }

}
