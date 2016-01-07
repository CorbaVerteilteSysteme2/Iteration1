/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardCore.MessageStorage;
import BoardModules.BasicServices.ViewServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;

/**
 *
 * @author Tobias
 */
public class ViewServiceImpl extends ViewServicePOA {

    private boolean state;
    
    @Override
    public Message[] getAllMessageByDestination(String destination) throws DestinationUnreachable {
        Message[] messages = null;
        
        if (destination.equals("")) {
            messages = (Message[]) MessageStorage.getInstance().getMessages().toArray();
        } else {
            // virtuelle Gruppe
        }
        
        return messages;
    }

    @Override
    public boolean getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
