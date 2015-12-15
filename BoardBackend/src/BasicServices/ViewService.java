/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

import BoardModules.BasicServices.ViewServicePOA;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;

/**
 *
 * @author Tobias
 */
public class ViewService extends ViewServicePOA {

    @Override
    public Message[] getAllMessageByDestination(String destination) throws DestinationUnreachable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
