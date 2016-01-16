/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BoardConfiguration.BoardConfiguration;
import BoardModules.BasicServices.BoardService;
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.BasicServices.ViewService;
import BoardModules.BasicServices.ViewServiceHelper;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.UnknownUser;
import BoardModules.User;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.EventListenerList;
import org.omg.CORBA.COMM_FAILURE;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author Tobias
 */
public class BoardFrontend {
    
    private final EventListenerList listeners = new EventListenerList();
    
    private final NamingContextExt nameService;
    private BoardService boardServiceObj;
    private ViewService viewServiceObj;
    private int localState;
    private final User user;
    private final ArrayList<Message> pufferedMessages;
    private final String tableID;
    
    public BoardFrontend(String tableID, String ipAddress, User user) throws NotFound, InvalidName, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        this.localState = -1;
        this.pufferedMessages = new ArrayList<>();
        this.user = user;
        this.tableID = tableID;
        ORB _orb;
        Properties props = new Properties();

        props.put("org.omg.CORBA.ORBInitialPort", BoardConfiguration.ORB_PORT);
        props.put("org.omg.CORBA.ORBInitialHost", ipAddress);

        _orb = ORB.init(new String[0], props);

        org.omg.CORBA.Object objRef = _orb.resolve_initial_references(BoardConfiguration.NAMESERVICE);
        this.nameService = NamingContextExtHelper.narrow(objRef);

        this.boardServiceObj = (BoardService) BoardServiceHelper.narrow(nameService.resolve_str(tableID + "/" + BoardConfiguration.BOARD_SERVICE_NAME));
        this.viewServiceObj = (ViewService) ViewServiceHelper.narrow(nameService.resolve_str(tableID + "/" + BoardConfiguration.VIEW_SERVICE_NAME));

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    int remoteState = viewServiceObj.getState();
                    if (localState != remoteState) {
                        try {
                            localState = remoteState;
                            notifyDisply(new NewMessageListEvent(this, viewServiceObj.getAllMessageByDestination("")));
                        } catch (DestinationUnreachable ex) {
                        }
                    } 
                } catch (COMM_FAILURE ex) {
                    boolean isWorking = false;
                    while (!isWorking) {
                        try {
                            boardServiceObj = (BoardService) BoardServiceHelper.narrow(nameService.resolve_str(tableID + "/" + BoardConfiguration.BOARD_SERVICE_NAME));
                            viewServiceObj = (ViewService) ViewServiceHelper.narrow(nameService.resolve_str(tableID + "/" + BoardConfiguration.VIEW_SERVICE_NAME));
                            
                            sendPufferedMessages();
                            isWorking = true;
                        } catch (NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | COMM_FAILURE ex1) {

                        }
                    }
                }
            }
        }, 0, 500);
    }
    
    // Puffer ist noch nicht threadsicher
    public void sendPufferedMessages() {
        if ((!pufferedMessages.isEmpty()) || (pufferedMessages != null)) {
            for (Message msg : pufferedMessages){
                try {
                    this.boardServiceObj.sendMessage(user, msg, tableID);
                } catch (UnknownUser ex) {
                    // wenn dies passiert, dann hat sich der Client 
                    // mit einer Tafel mit der gleichen ID, aber anderen Nutzern angemeldet
                }
            }
            pufferedMessages.clear();
        }
    }
    
    public void checkUser(User user) throws UnknownUser {
        this.boardServiceObj.checkUser(user);
    }
    
    void sendMessage(User user, Message message, String tableID) throws UnknownUser {
        try {
            this.boardServiceObj.sendMessage(user, message, tableID);
        } catch (COMM_FAILURE ex) {
            this.pufferedMessages.add(message);
            throw ex;
        }
                
    }
    
    public void removeMessage(User user, Message message) throws UnknownUser {
        this.boardServiceObj.removeMessage(user, message, "");
    }
    
    public void addListener(NewMessageListListener listener) {
        this.listeners.add(NewMessageListListener.class, listener);
    }
    
    public void remove(NewMessageListListener listener) {
        this.listeners.remove(NewMessageListListener.class, listener);
    }
    
    protected synchronized void notifyDisply(NewMessageListEvent event) {
        for (NewMessageListListener listener : listeners.getListeners(NewMessageListListener.class)) {
            listener.displayMessageList(event);
        }
    }
    
    // Klassen
    public class NewMessageListEvent extends EventObject {
        
        private final Message[] messages;
        
        public NewMessageListEvent(Object source, Message[] messages) {
            super(source);
            this.messages = messages;
        }
        
        public Message[] getMessages() {
            return this.messages;
        }
    }
    
    public interface NewMessageListListener extends EventListener {
        void displayMessageList(NewMessageListEvent e);
    }
}
