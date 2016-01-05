/*
 *
 */
package BoardCore;

import AdvancedServices.*;
import BasicServices.*;

import BoardModules.BasicServices.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

/**
 * Der BoardCore ist das zentrale Element einer Tafel.
 * Hier werden: 
 *      - der Zugriff auf den ORB realisiert
 *      - auf den Namensdienst zugegriffen
 *      - die Services gestartet
 *      - ...
 * Der BoardCore ist kein Thread.
 * @author Tobias Müller
 */
public class BoardCore extends AbstractCore {
    
    //private VirtualBoardServiceImpl _virtualBoardService;
    
    /**
     * Konstruktor
     * 
     * @param boardID Tafel-Identifier
     * @throws org.omg.CosNaming.NamingContextPackage.CannotProceed
     * @throws org.omg.CosNaming.NamingContextPackage.InvalidName
     */
    public BoardCore(String boardID) throws RuntimeException, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        super(boardID); 
    }
    
    /*
    public void run() {
        
        while (true) {
            _orb.run();
        }
        
    }
     */
    /**
     * Methode dient zum Initialisieren des ORBs.
     * 
     * @param port Port des ORBs
     * @param host Host, auf dem der ORB läuft
     */
    /*
    private void initializeORB(String port, String host) {
        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", port);
        props.put("org.omg.CORBA.ORBInitialHost", host);

        this._orb = ORB.init(new String[0], props);
    }
*/
}
