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
     * @param source (ehemals destination) gibt die Quelle der nachricht an
     * @throws UnknownUser 
     */
    @Override
    public void sendMessage(User user, Message message, String source) throws UnknownUser {
        System.out.println("Einkommende Nachricht...");
        if (source.equals(core.getIdentifier())) {
            // in diesem Fall überträgt ein Benutzer der lokalen Tafel eine Nachricht 

            // Benutzer überprüfen
            if (!core.checkUser(user)) {
                throw new UnknownUser(user.name);
            }
            System.out.println("Nachricht erhalten von " + message.author + ": " + message.content + ", vom " + message.timestamp);
            core.addMessage(message);
        } else {
            // hier handelt es sich bei der Nachricht um eine weitergeleitete Nachricht
            System.out.println("Nachricht erhalten von " + source + "-" + message.author + ": " + message.content + ", vom " + message.timestamp);
            message.author = message.author + "(" + source + ")";
            core.addMessage(message);
        }
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

    @Override
    public void removeMessage(User user, Message message, String source) throws UnknownUser {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
