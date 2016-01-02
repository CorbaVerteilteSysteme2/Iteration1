/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import AdvancedServices.*;
import BasicServices.*;

import BoardModules.BasicServices.*;
import BoardModules.AdvancedServices.*;
import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * Der BoardCore ist das zentrale Element einer Tafel.
 * Hier werden: 
 *      - der Zugriff auf den ORB realisiert
 *      - auf den Namensdienst zugegriffen
 *      - die Services gestartet
 *      - ...
 * 
 * @author Tobias
 */
public class BoardCore {
    
    private ORB _orb;
    private String _boardIdentifer;
    private BoardServiceImpl _boardService;
    private AdministrationServiceImpl _adminService;
    private ViewServiceImpl _viewService;
    private VirtualBoardServiceImpl _virtualBoardService;
    
    
    public BoardCore(String[] args) {
        try {
            this._boardIdentifer = "Test-Table1";
            
            // initialisierung ORB
            //String[] orbInitial = {"-ORBInitialPort ",  orbInitialPort, " -ORBInitialHost ", orbInitialHost };
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            
            this._orb = ORB.init(args, props);
            
            POA rootPOA = POAHelper.narrow(_orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
  
            
            org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
            NamingContextExt initContext = NamingContextExtHelper.narrow(objRef);
            
            this._boardService = new BoardServiceImpl();
            this._adminService = new AdministrationServiceImpl();
            this._viewService = new ViewServiceImpl();
            this._virtualBoardService = new VirtualBoardServiceImpl();
            
            org.omg.CORBA.Object boardServiceRef = rootPOA.servant_to_reference(_boardService);
            org.omg.CORBA.Object adminServiceRef = rootPOA.servant_to_reference(_adminService);
            org.omg.CORBA.Object viewServiceRef = rootPOA.servant_to_reference(_viewService);
            org.omg.CORBA.Object virtualBoardServiceRef = rootPOA.servant_to_reference(_virtualBoardService);
            
            BoardService boardService = BoardServiceHelper.narrow(boardServiceRef);
            AdministrationService adminService = AdministrationServiceHelper.narrow(adminServiceRef);
            ViewService viewService = ViewServiceHelper.narrow(viewServiceRef);
            VirtualBoardService virtualBoardService = VirtualBoardServiceHelper.narrow(virtualBoardServiceRef);
            
            NameComponent boardNC = new NameComponent(_boardIdentifer, "");
            NameComponent boardServiceNC = new NameComponent("BoardService", "");
            NameComponent adminServiceNC = new NameComponent("AdminService", "");
            NameComponent viewServiceNC = new NameComponent("ViewService", "");
            NameComponent virtualBoardServiceNC = new NameComponent("VirtualBoardService", "");

            
            NameComponent path1[] = { boardNC, boardServiceNC };
            NameComponent path2[] = { boardNC, adminServiceNC };
            NameComponent path3[] = { boardNC, viewServiceNC, };
            NameComponent path4[] = { boardNC, virtualBoardServiceNC };
            
            
            
            registerObjWithNameService(initContext, path1, boardServiceRef);
            registerObjWithNameService(initContext, path2, adminServiceRef);
            registerObjWithNameService(initContext, path3, viewServiceRef);
            registerObjWithNameService(initContext, path4, virtualBoardServiceRef);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
    public void run() {
        while (true) {
            _orb.run();
        }
    }
    
    /**
     * Methode bindet einen Service in den Namensdienst von CORBA ein.
     * Danach kann auf den Service zugegriffen werden!
     * 
     * @param serviceName
     * @param serviceRef
     * @param namingContextRef
     * @throws NotFound
     * @throws CannotProceed
     * @throws InvalidName 
     */
    private void registerObjWithNameService(NamingContext root, NameComponent[] serverName, org.omg.CORBA.Object obj) throws NotFound, AlreadyBound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        NamingContext currentContext = root;
        
        NameComponent[] singleElement = new NameComponent[1];
        for (int i = 0; i < serverName.length - 1; i++) {
            try {
                singleElement[0] = serverName[i];
                currentContext = NamingContextHelper.narrow(currentContext.resolve(singleElement));
            } catch (NotFound ex) {
                currentContext = currentContext.bind_new_context(singleElement);
            } 
        }
        singleElement[0] = serverName[serverName.length - 1];
        currentContext.rebind(singleElement, obj);
    }
}
