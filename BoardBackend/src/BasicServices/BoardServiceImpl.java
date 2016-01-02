/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicServices;

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
    
    @Override
    public void sendMessage(User user, Message message, String destination) throws DestinationUnreachable, UnknownUser {
        System.out.println("Testausgabe auf dem Server!");
    }

    @Override
    public String[] getAllVirtualGroups() {
        System.out.println("Testausgabe auf dem Server!");
        return null;
    }

    @Override
    public void checkUser(User user) throws UnknownUser {
        System.out.println("Testausgabe auf dem Server!");
    }
    
}
