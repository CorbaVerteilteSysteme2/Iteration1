/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

import BoardConfiguration.BoardConfiguration;

/**
 * 
 * 
 * @author Tobias
 */
public class BoardCoreApplication {
    
    public static void main(String[] args) {
        
        try {          
            BoardCore core = null;
            System.out.println("BoardCore wird gestartet...");
            
            if (args.length == 2) {
                System.out.println("Port: " + BoardConfiguration.ORB_PORT + "; Host: " + args[0]);
                System.out.println("Tafel-Identifier: " + args[1]);
                ORBAccessControl.getInstance().setORB(BoardConfiguration.ORB_PORT, args[0]);
                core = new BoardCore(args[1]);
            } else {
                // Testausführung!
                String identifier = "Test-Tafel2";
                System.out.println("Test-Ausführung!\n" + identifier + " auf Port " + BoardConfiguration.ORB_PORT);
                ORBAccessControl.getInstance().setORB(BoardConfiguration.ORB_PORT, "localhost");
                core = new BoardCore(identifier);
            }
            
            Runtime.getRuntime().addShutdownHook(new LastResort(core));
        
            ORBAccessControl.getInstance().run();
            //core.run();
        } catch (Exception ex) {
            System.err.println("Fehler: " + ex.getMessage());
        } 
    }

    private static class LastResort extends Thread {

        BoardCore core;
        private LastResort(BoardCore core) {
            this.core = core;
        }

        @Override
        public void run() {
            if (core != null) {
                System.out.println("BoardCore wurde beendet!");
                core.closeCore();
            }
            ORBAccessControl.getInstance().shutdown();
        }
    }
}
