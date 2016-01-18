/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Mike
 * @version 0.1
 * @date 08.01.2016
 */
import BoardModules.BasicServices.BoardService;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.UnknownUser;
import BoardModules.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import javax.swing.JOptionPane;
import org.omg.CORBA.COMM_FAILURE;

public class UserGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public UserGUI() {
        initComponents();
        loginDialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginDialog = new javax.swing.JDialog();
        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        userBoardInput = new javax.swing.JTextField();
        userNameInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        IPInput = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        sendMessageField = new javax.swing.JTextArea();
        sendMessage = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        readMessageField = new javax.swing.JTextArea();
        delMsgNrInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        loginDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loginDialog.setAlwaysOnTop(true);
        loginDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        loginDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                loginDialogWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                loginDialogWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tafelname:");

        loginButton.setText("anmelden");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Benutzername:");

        userBoardInput.setText("Test-Tafel2");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("IP-Adresse");

        IPInput.setText("localhost");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(loginButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(IPInput, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userNameInput, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userBoardInput, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(128, 128, 128))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(userBoardInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IPInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(loginButton)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout loginDialogLayout = new javax.swing.GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sendMessageField.setColumns(20);
        sendMessageField.setRows(5);
        jScrollPane2.setViewportView(sendMessageField);

        sendMessage.setText("Senden");
        sendMessage.setActionCommand("sendMessage");
        sendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageActionPerformed(evt);
            }
        });

        readMessageField.setEditable(false);
        readMessageField.setColumns(20);
        readMessageField.setRows(5);
        jScrollPane3.setViewportView(readMessageField);

        jLabel4.setText("Nachricht-Nummer:");

        jButton1.setText("Löschen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delMsgNrInput, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(sendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delMsgNrInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Änderung: Destination ist nun die Quelle einer Nachricht
     * @throws DestinationUnreachable
     * @throws UnknownUser 
     */
    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed
        sendMessage(sendMessageField.getText());
        sendMessageField.setText("");
    }

    private void sendMessage(String message) {
        if ("".equals(message)) {
            JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.MESSAGE_EMPTY, "Warnung", JOptionPane.WARNING_MESSAGE);
        } else {
            SendMessageWorker worker = new SendMessageWorker();
            worker.SetMessageContent(message);
            worker.execute();
        }
    }//GEN-LAST:event_sendMessageActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
        if (startUserBoardService(userNameInput.getText(),userBoardInput.getText(), IPInput.getText())){
            //TODO Anpassen wenn mehrere Tafeln auswaehlbar werden
            //destinationList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {userBoardInput.getText()}));
        
            //GUI
            this.setEnabled(true);
//            t.start();
            loginDialog.setVisible(false);
            
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void loginDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loginDialogWindowOpened
        //GUI
        this.setEnabled(false);
    }//GEN-LAST:event_loginDialogWindowOpened

    private void loginDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loginDialogWindowClosed
        //GUI
        this.dispose();
    }//GEN-LAST:event_loginDialogWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteMessage(Integer.parseInt(delMsgNrInput.getText()));  
        delMsgNrInput.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserGUI().setVisible(true);
            }
        });
    }
    
    /**
    * @param username Benutzername zum einloggen.
    * @param tableID Name der Tafel auf die eingeloggt werden soll.
    * @param ipAddress IP-Adresse des Nameservers.
    * @author Tobias Müller, Mike Hoffmann
    * @return Status Login Erfolgreich/Fail
    */
    public boolean startUserBoardService(String username, String tableID, String ipAddress){
        boolean worked = false;
        this.tableID = tableID;
        this.setTitle("UserApp: " + tableID + "/" + username);
        try {
            this.user = new User(username);
            this._boardFrontend = new BoardFrontend(this.tableID, ipAddress, user, BoardFrontend.FrontendMode.User);
            this._boardFrontend.checkUser(user);
            worked = true;
            this._boardFrontend.addListener(new NewMessageListListenerImpl(this));
            SendMessageWorker.setBoardFrontend(_boardFrontend);
        } catch (UnknownUser ex){
            JOptionPane.showMessageDialog(null,BoardFrontendConfiguration.USER_NOT_FOUND,"Warnung",JOptionPane.WARNING_MESSAGE);     
        } catch (InvalidName | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        }catch (COMM_FAILURE ex){
            JOptionPane.showMessageDialog(null,BoardFrontendConfiguration.SERVER_NOT_FOUND,"Warnung",JOptionPane.WARNING_MESSAGE);
        } catch (NotFound ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,BoardFrontendConfiguration.TABLE_NOT_FOUND,"Warnung",JOptionPane.WARNING_MESSAGE);
        }

        return worked;
    }    

    private void deleteMessage(int msgNr) {
        try {
            if ((msgNr < message.length) && (msgNr >= 0) && (message != null)) {
                this._boardFrontend.removeMessage(user, message[msgNr]);
                JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.MESSAGE_DELETED, "Warnung", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.INPUT_FAILURE, "Warnung", JOptionPane.WARNING_MESSAGE);
            }

        } catch (UnknownUser ex) {
            JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.MESSAGE_DELETE_NOT_ALLOWED, "Warnung", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.MESSAGE_NUMBER_FAILURE, "Warnung", JOptionPane.WARNING_MESSAGE);
        } catch (COMM_FAILURE ex) {
            JOptionPane.showMessageDialog(null, BoardFrontendConfiguration.SERVER_NOT_FOUND, "Warnung", JOptionPane.WARNING_MESSAGE);
        }
    }
 
    private void printMessageList(Message[] messages) {
        int counter = 0;
        
        this.message = messages;
        readMessageField.setText("");
        for (Message msg : messages) {
            readMessageField.append(Integer.toString(counter) + ": ");
            readMessageField.append(msg.toString());
            readMessageField.append("\n");
            counter++;
        }
        readMessageField.setCaretPosition(readMessageField.getText().length());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IPInput;
    private javax.swing.JTextField delMsgNrInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loginButton;
    private javax.swing.JDialog loginDialog;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextArea readMessageField;
    private javax.swing.JButton sendMessage;
    private javax.swing.JTextArea sendMessageField;
    private javax.swing.JTextField userBoardInput;
    private javax.swing.JTextField userNameInput;
    // End of variables declaration//GEN-END:variables
    private User user = null;
    private Message[] message = null;
    private String tableID = "";
    private BoardFrontend _boardFrontend;

    private class NewMessageListListenerImpl implements BoardFrontend.NewMessageListListener {

        private final UserGUI parent;
       
        private NewMessageListListenerImpl(UserGUI parent) {
            this.parent = parent;
        }

        @Override
        public void displayMessageList(BoardFrontend.NewMessageListEvent e) {
            this.parent.printMessageList(e.getMessages());
        }
    }
    
}


