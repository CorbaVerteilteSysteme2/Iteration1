/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

/**
 *
 * @author Tobias
 */
public class ORBAccessControl {
    private static ORBAccessControl instance;
    private ORB _orb;
    private POA rootPOA;
    private NamingContextExt nameService;
    
    public static synchronized ORBAccessControl getInstance() {
        if (instance == null) {
            instance = new ORBAccessControl();
        }
        
        return instance;
    }
    
    private ORBAccessControl() {
        
    }
    
    public void setORB(String orbPort, String orbHost) throws InvalidName, AdapterInactive {
        if (_orb == null) {
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", orbPort);
            props.put("org.omg.CORBA.ORBInitialHost", orbHost);

            this._orb = ORB.init(new String[0], props);

            rootPOA = POAHelper.narrow(_orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
            nameService = NamingContextExtHelper.narrow(objRef);
        }
    }
    
    public POA getRootPOA() {
        return rootPOA;
    }
    
    public NamingContextExt getNameService() {
        return nameService;
    }
    
    public void run() {
        _orb.run();
    }
    
    public void shutdown() {
        this._orb.shutdown(false);
    }
}
 