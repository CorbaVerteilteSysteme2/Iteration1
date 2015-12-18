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
    private POA _rootPOA;
    private String _boardIdentifer;
    private BoardServiceImpl _boardService;
    private AdministrationServiceImpl _adminService;
    private ViewServiceImpl _viewService;
    private VirtualBoardServiceImpl _virtualBoardService;
    
    
    public BoardCore(String[] args) {
        try {
            this._boardIdentifer = "Test-Tafel";
            
            // initialisierung ORB
            //String[] orbInitial = {"-ORBInitialPort ",  orbInitialPort, " -ORBInitialHost ", orbInitialHost };
            this._orb = ORB.init(args, null);
            
            _rootPOA = POAHelper.narrow(_orb.resolve_initial_references("RootPOA"));
            _rootPOA.the_POAManager().activate();
            
            this._boardService = new BoardServiceImpl();
            this._adminService = new AdministrationServiceImpl();
            this._viewService = new ViewServiceImpl();
            this._virtualBoardService = new VirtualBoardServiceImpl();
            
            org.omg.CORBA.Object boardServiceRef = _rootPOA.servant_to_reference(_boardService);
            org.omg.CORBA.Object adminServiceRef = _rootPOA.servant_to_reference(_adminService);
            org.omg.CORBA.Object viewServiceRef = _rootPOA.servant_to_reference(_viewService);
            org.omg.CORBA.Object virtualBoardServiceRef = _rootPOA.servant_to_reference(_virtualBoardService);
            
            BoardService boardService = BoardServiceHelper.narrow(boardServiceRef);
            AdministrationService adminService = AdministrationServiceHelper.narrow(adminServiceRef);
            ViewService viewService = ViewServiceHelper.narrow(viewServiceRef);
            VirtualBoardService virtualBoardService = VirtualBoardServiceHelper.narrow(virtualBoardServiceRef);
            
            org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            NameComponent home = new NameComponent(_boardIdentifer, "");
            
            
            
            bindServiceToNameService(home, "BoardService", boardService, ncRef);
            bindServiceToNameService(home, "AdministrationService", adminService, ncRef);
            bindServiceToNameService(home, "ViewService", viewService, ncRef);
            bindServiceToNameService(home, "VirtualBoardService", virtualBoardService, ncRef);
            
            while (true) {
                _orb.run();
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
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
    private void bindServiceToNameService(NameComponent home, String serviceName, org.omg.CORBA.Object serviceRef, NamingContextExt namingContextRef) throws NotFound, CannotProceed, InvalidName, org.omg.CosNaming.NamingContextPackage.InvalidName, AlreadyBound {
        
        NameComponent path[] = {home, new NameComponent(serviceName, "")};
        NamingContext cxt = namingContextRef.new_context();
        cxt.bind_new_context(path);
    }
    
    /*
    private void initializeService(Servant servant, String serviceName, Class<?> serviceClass, Class<?> helperClass) throws ServantNotActive, WrongPolicy, InstantiationException, IllegalAccessException {
        org.omg.CORBA.Object ref = _rootPOA.servant_to_reference(servant);
        
        //serviceClass service = helperClass.;
    }
    */
}
