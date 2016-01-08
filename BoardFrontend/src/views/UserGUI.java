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
import BoardModules.BasicServices.BoardServiceHelper;
import BoardModules.DestinationUnreachable;
import BoardModules.Message;
import BoardModules.UnknownUser;
import BoardModules.User;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import BoardModules.BasicServices.*;
import java.awt.event.*;

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
        destinationList = new javax.swing.JComboBox<>();

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

        userBoardInput.setText("Test-Tafel");

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

        sendMessage.setText("senden");
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

        destinationList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "eigene Tafel" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(destinationList, 0, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendMessage)
                .addContainerGap())
            .addComponent(jScrollPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendMessage)
                    .addComponent(destinationList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @throws DestinationUnreachable
     * @throws UnknownUser 
     */
    private void sendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageActionPerformed
        String destination;
        destination = (String) destinationList.getSelectedItem();
        //if ("eigene Tafel".equals(destination))
        //    destination = "";
        //Nachricht Senden
        try{
        boardServiceObj.sendMessage(user, new Message(sendMessageField.getText(), user.name, new Date().toString()), destination);
        } catch (DestinationUnreachable ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownUser ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TODO Eingehende Nachrichten Anzeige
        //Verruebergehende Ausgabe
        readMessageField.append(sendMessageField.getText());
        readMessageField.append("\n");
        sendMessageField.setText("");
    }//GEN-LAST:event_sendMessageActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
        if (startUserBoardService(userNameInput.getText(),userBoardInput.getText(), IPInput.getText())){
            //TODO Anpassen wenn mehrere Tafeln auswaehlbar werden
            destinationList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {userBoardInput.getText()}));
        
            //GUI
            this.setEnabled(true);
            t.start();
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
        try {
            ORB _orb;
            Properties props = new Properties();
            
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", ipAddress);
            
            _orb = ORB.init(new String[0], props);
            
            org.omg.CORBA.Object objRef = _orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        
            this.boardServiceObj = (BoardService) BoardServiceHelper.narrow(ncRef.resolve_str(tableID + "/BoardService"));
            this.viewServiceObj = (ViewService) ViewServiceHelper.narrow(ncRef.resolve_str(tableID + "/ViewService"));
            
            this.user = new User(username);
            worked = true;
        } catch (InvalidName ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFound ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotProceed ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            Logger.getLogger(BoardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return worked;
    }    
 
    javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {  
        @Override
        public void actionPerformed(ActionEvent e) {
              try{
              messageCheck = viewServiceObj.getAllMessageByDestination("");
              if (message != messageCheck){
                message = messageCheck;  
                readMessageField.setText("");
                for (int i = 0; i < message.length; i++){
                    readMessageField.append(message[i].toString());
                    readMessageField.append("\n");
              }
              }
              }catch (DestinationUnreachable ex){
                  
              }
          }
    }
    );
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IPInput;
    private javax.swing.JComboBox<String> destinationList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private BoardService boardServiceObj = null;
    private ViewService viewServiceObj = null;
    private User user = null;
    private Message[] message = null;
    private Message[] messageCheck = null;
    //private String tableID = "";
}
