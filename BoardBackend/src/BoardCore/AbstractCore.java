/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import BasicServices.AdministrationServiceImpl;
import BasicServices.BoardServiceImpl;
import BasicServices.ViewServiceImpl;
import BoardModules.BasicServices.AdministrationService;
import BoardModules.BasicServices.AdministrationServiceHelper;
import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.BasicServices.ViewService;
import BoardModules.BasicServices.ViewServiceHelper;
import BoardModules.Message;
import BoardModules.User;
import MessageStorage.MessageParser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author Tobias
 */
public abstract class AbstractCore {
    
    protected String _identifier;
    protected BoardServiceImpl _boardService;
    protected AdministrationServiceImpl _adminService;
    protected ViewServiceImpl _viewService;
    
    private ArrayList<Message> messages;
    
    private MessageParser messageParser;
    
    public AbstractCore(String identifier) throws CannotProceed, InvalidName {
        try {
            this._identifier = identifier;
            this.messageParser = new MessageParser(identifier);
            this.messages = new ArrayList<>();
            //getAllMessagesFromMessageParser();
            
            this._boardService = new BoardServiceImpl(this);
            this._adminService = new AdministrationServiceImpl(this);
            this._viewService = new ViewServiceImpl(this);
            
            org.omg.CORBA.Object boardServiceRef = ORBAccessControl.getInstance().getRootPOA().servant_to_reference(_boardService);
            org.omg.CORBA.Object adminServiceRef = ORBAccessControl.getInstance().getRootPOA().servant_to_reference(_adminService);
            org.omg.CORBA.Object viewServiceRef = ORBAccessControl.getInstance().getRootPOA().servant_to_reference(_viewService);
            
            BoardService boardService = BoardServiceHelper.narrow(boardServiceRef);
            AdministrationService adminService = AdministrationServiceHelper.narrow(adminServiceRef);
            ViewService viewService = ViewServiceHelper.narrow(viewServiceRef);

            NameComponent boardNC = new NameComponent(_identifier, "");

            NameComponent boardServiceNC = new NameComponent("BoardService", "");
            NameComponent adminServiceNC = new NameComponent("AdminService", "");
            NameComponent viewServiceNC = new NameComponent("ViewService", "");

            NameComponent path1[] = { boardNC, boardServiceNC };
            NameComponent path2[] = { boardNC, adminServiceNC };
            NameComponent path3[] = { boardNC, viewServiceNC, };

            registerObjWithNameService(ORBAccessControl.getInstance().getNameService(), path1, boardServiceRef);
            registerObjWithNameService(ORBAccessControl.getInstance().getNameService(), path2, adminServiceRef);
            registerObjWithNameService(ORBAccessControl.getInstance().getNameService(), path3, viewServiceRef);
            
        } catch (ServantNotActive ex) {
            Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPolicy ex) {
            Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBound ex) {
            Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Methode bindet einen Service in den Namensdienst von CORBA ein.
     * Danach kann auf den Service zugegriffen werden!
     * 
     * @param root Unterverzeichnis des zu bindenden Dienstes
     * @param serviceName Name des Dienstes
     * @param serviceObj Objekt des Dienstes
     * @throws NotFound
     * @throws CannotProceed
     * @throws InvalidName 
     */
    protected final void registerObjWithNameService(NamingContext root, NameComponent[] serviceName, org.omg.CORBA.Object serviceObj) throws NotFound, AlreadyBound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        NamingContext currentContext = root;
        
        NameComponent[] singleElement = new NameComponent[1];
        for (int i = 0; i < serviceName.length - 1; i++) {
            try {
                singleElement[0] = serviceName[i];
                currentContext = NamingContextHelper.narrow(currentContext.resolve(singleElement));
            } catch (NotFound ex) {
                currentContext = currentContext.bind_new_context(singleElement);
            } 
        }
        singleElement[0] = serviceName[serviceName.length - 1];
        currentContext.rebind(singleElement, serviceObj);
    }
    
    public synchronized void addMessage(Message msg) {
        messages.add(msg);
        //messageParser.WriteMessageToTextfile(msg);
        _viewService.setState(true);
        
    }
    
     public synchronized void removeMessage(Message msg) {
        this.messages.remove(msg);
        _viewService.setState(true);
    }
     
    public synchronized Message[] getAllMessages() {
        Message msgs[] = new Message[messages.size()];
        
        for (int i = 0; i < messages.size(); i++) {
            msgs[i] = messages.get(i);
        }
        
        return msgs;
    }
    
    public abstract void addUser(User user);    
    
    public abstract boolean checkUser(User user);
    
    private void getAllMessagesFromMessageParser() {
        messages = messageParser.ReadMessageLogFromTextfile(_identifier);
    }
    
    public String getIdentifier() {
        return _identifier;
    }

    public abstract User[] getAllUsers();
}
