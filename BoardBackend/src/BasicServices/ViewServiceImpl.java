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
        this.state = 0;
    }
    
    /**
     * Diese Variable wird bei jeder Änderung der Nachrichtenliste um 1 inkrementiert.
     * Sie zeigt die Anzahl an Änderungen an.
     */
    private int state;
    
    public void incrementState() {
        this.state++;
    }
    
    @Override
    public Message[] getAllMessageByDestination(String destination) throws DestinationUnreachable {
        return core.getAllMessages();
    }

    /**
     * Mithilfe dieser Methode können Clients prüfen, ob die Nachrichtenliste verändert wurde
     * @return 
     */
    @Override
    public int getState() {
        return state;
    }
    
}
