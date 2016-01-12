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
            BoardCore core = null;
            System.out.println("BoardCore wird gestartet...");
            

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
                core = new BoardCore("Test-Tafel2");
            }
            
            Runtime.getRuntime().addShutdownHook(new ThreadImpl(core));
        
            ORBAccessControl.getInstance().run();
            //core.run();
        } catch (Exception ex) {
            System.err.println("Fehler: " + ex.getMessage());
        } 
    }

    private static class ThreadImpl extends Thread {

        BoardCore core;
        private ThreadImpl(BoardCore core) {
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
