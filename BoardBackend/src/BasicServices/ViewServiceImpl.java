/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardCore.AbstractCore;
import BoardModules.BasicServices.ViewServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;

/**
 *
 * @author Tobias
 */
public class ViewServiceImpl extends ViewServicePOA {

    private final AbstractCore core;
    
    public ViewServiceImpl(AbstractCore core) {
        super();
        
        this.core = core;
    }
    
    private boolean state;
    
    public void setState(boolean messages_changed) {
        this.state = messages_changed;
    }
    
    @Override
    public Message[] getAllMessageByDestination(String destination) throws DestinationUnreachable {
        return core.getAllMessages();
    }

    /**
     * Methode gibt zurück, ob sich die Nachrichten verändert haben:
     * true - Nachrichten wurden verändert
     * false - Nachrichten wurden nicht verändert
     * @return 
     */
    @Override
    public boolean getState() {
        return this.state;
    }
    
}
