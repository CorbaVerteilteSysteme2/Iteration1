/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardCore.AbstractCore;
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

    private final AbstractCore core;
    
    public BoardServiceImpl(AbstractCore core) {
        this.core = core;
    }
    
    /**
     * 
     * @param user
     * @param message
     * @param destination depricated
     * @throws DestinationUnreachable
     * @throws UnknownUser 
     */
    @Override
    public void sendMessage(User user, Message message, String destination) throws DestinationUnreachable, UnknownUser {
        // Benutzer überprüfen
        System.out.println("Nachricht erhalten von " + message.author + ": " + message.content + ", vom " + message.timestamp);
        // Ziel unterscheiden zwischen lokale Tafel oder VirtualBoardService
        // destination == "" --> Ziel ist die lokale Tafel! 
//        if (destination.equals("")) {
//            MessageStorage.getInstance().addMessage(message);
//        } else {
//            // hier wird an eine virtuelle Gruppe gesendet
//        }
        core.addMessage(message);
    }

    /**
     * 
     * @param user
     * @throws UnknownUser 
     */
    @Override
    public void checkUser(User user) throws UnknownUser {
        if (!core.checkUser(user)) {
            throw new UnknownUser(user.name);
        }
    }

}
