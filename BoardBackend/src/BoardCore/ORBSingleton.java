/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import org.omg.CORBA.ORB;

/**
 *
 * @author Tobias
 */
public class ORBSingleton {
    
    private ORB _orb;
    
    public ORB getORB() {
        return this._orb;
    }
    
    private ORBSingleton() {
        
    }
    
    public static ORBSingleton getInstance() {
        return ORBSingletonHolder.INSTANCE;
    }
    
    private static class ORBSingletonHolder {

        private static final ORBSingleton INSTANCE = new ORBSingleton();
    }
}
