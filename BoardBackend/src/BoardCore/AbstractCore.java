/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import BasicServices.AdministrationServiceImpl;
import BasicServices.BoardServiceImpl;
import BasicServices.ViewServiceImpl;
import BoardConfiguration.BoardConfiguration;
import BoardCore.Helper.MessageComparator;
import BoardModules.BasicServices.AdministrationService;
import BoardModules.BasicServices.AdministrationServiceHelper;
import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.BasicServices.ViewService;
import BoardModules.BasicServices.ViewServiceHelper;
import BoardModules.Message;
import BoardModules.User;
import Interfaces.*;
import MessageStorage.SaveMessage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
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
    
    private ArrayList<Message> localMessages;
    
    private IMessageStorage _messageStorage;
    
    public AbstractCore(String identifier) throws CannotProceed, InvalidName {
        try {
            this._identifier = identifier;
            
            this._messageStorage = new SaveMessage();
            
            this.localMessages = _messageStorage.loadAllMessages(_identifier);
            
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

            NameComponent boardServiceNC = new NameComponent(BoardConfiguration.BOARD_SERVICE_NAME, "");
            NameComponent adminServiceNC = new NameComponent(BoardConfiguration.ADMIN_SERVICE_NAME, "");
            NameComponent viewServiceNC = new NameComponent(BoardConfiguration.VIEW_SERVICE_NAME, "");

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
     * @throws org.omg.CosNaming.NamingContextPackage.AlreadyBound
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
        localMessages.add(msg);
        sortMessages();
        this._viewService.incrementState();
    }
    
    protected void closeCore() {
        NamingContextExt nameService = ORBAccessControl.getInstance().getNameService();
        
        NameComponent boardNC = new NameComponent(_identifier, "");
        
        if (_viewService != null) {
            try {
                nameService.unbind(new NameComponent[] { boardNC, new NameComponent(BoardConfiguration.VIEW_SERVICE_NAME, "")});
            } catch (NotFound ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotProceed ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (_boardService != null) {
            try {
                nameService.unbind(new NameComponent[] { boardNC, new NameComponent(BoardConfiguration.BOARD_SERVICE_NAME, "")});
            } catch (NotFound ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotProceed ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (_adminService != null) {
            try {
                nameService.unbind(new NameComponent[] { boardNC, new NameComponent(BoardConfiguration.ADMIN_SERVICE_NAME, "")});
            } catch (NotFound ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotProceed ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(AbstractCore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this._messageStorage.storeMessageList(_identifier, localMessages);
    }
    
     public synchronized void removeMessage(Message msg) {
        this.localMessages.remove(msg);
        this._viewService.incrementState();
    }
     
    public synchronized Message[] getAllMessages() {
        Message msgs[] = new Message[localMessages.size()];
        
        for (int i = 0; i < localMessages.size(); i++) {
            msgs[i] = localMessages.get(i);
        }
        
        return msgs;
    }
    
    protected void sortMessages() {
        this.localMessages.sort(new MessageComparator());
    }
    
    public abstract void addUser(User user);   
    
    public abstract boolean removeUser(User user);
    
    public abstract boolean checkUser(User user);
       
    public String getIdentifier() {
        return _identifier;
    }

    public abstract User[] getAllUsers();
    
    /* nur als Idee (TM) - lokale Nachrichten und Nachrichten von anderen Tafeln trennen
    public void addForwardedMessage(String boardname, Message message) {
        
    }
    */
}
