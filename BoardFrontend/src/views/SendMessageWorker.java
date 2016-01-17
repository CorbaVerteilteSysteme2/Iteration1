/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import BoardModules.BasicServices.BoardService;
import BoardModules.Message;
import BoardModules.UnknownUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.omg.CORBA.COMM_FAILURE;

/**
 *
 * @author Tobias
 */
public class SendMessageWorker extends SwingWorker<Boolean, Message> {
    
    private String msgContent;
    public void SetMessageContent(String content) {
        this.msgContent = content;
    }
    
    private static BoardFrontend _boardFrontend;
    public static void setBoardFrontend(BoardFrontend boardFrontend) {
        _boardFrontend = boardFrontend;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        try {

            _boardFrontend.sendMessage(msgContent);
        } catch (UnknownUser ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unbekannter Nutzer!", "Warnung", JOptionPane.WARNING_MESSAGE);
            
        } catch (COMM_FAILURE ex) {

        }

        return true;
    }

}
