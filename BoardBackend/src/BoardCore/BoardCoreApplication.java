/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

/**
 * 
 * 
 * @author Tobias
 */
public class BoardCoreApplication {
    
    public static void main(String[] args) {
        try {
            System.out.println("BoardCore wird gestartet...");
            BoardCore core = null;

            int port = 1050;
            
            if (args.length == 2) {
                System.out.println("Port: " + port + "; Host: " + args[0]);
                System.out.println("Tafel-Identifier: " + args[1]);
                ORBAccessControl.getInstance().setORB(Integer.toString(port), args[0]);
                core = new BoardCore(args[1]);
            } else {
                // Testausführung!
                System.out.println("Test-Ausführung!");
                ORBAccessControl.getInstance().setORB("1050", "localhost");
                core = new BoardCore("Test-Tafel");
            }
            ORBAccessControl.getInstance().run();
            //core.run();
        } catch (Exception ex) {
            System.err.println("Fehler: " + ex.getMessage());
        } finally {
//            System.out.println("BoardCore wird heruntergefahren...");
//            ORBAccessControl.getInstance().shutdown();
//            System.out.println("Board wurde beendet.");
        }
    }
}
