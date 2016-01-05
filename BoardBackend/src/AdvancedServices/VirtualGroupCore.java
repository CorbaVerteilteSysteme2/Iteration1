/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdvancedServices;

import BoardCore.AbstractCore;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

/**
 *
 * @author Tobias
 */
public class VirtualGroupCore extends AbstractCore {
    
    public VirtualGroupCore(String groupname) throws CannotProceed, InvalidName {
        super(groupname);
        
    }

}
