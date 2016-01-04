/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardCore;

/**
 * 
 * @author Tobias
 */
public class BoardCoreApplication {
    
    public static void main(String[] args) {
        BoardCore core = null;
         
        if (args.length == 3) {
            core = new BoardCore(args[0], args[1], args[2]);
        } else {
            // Testausführung!
            core = new BoardCore("Test-Tafel", "1050", "localhost");
        }
        
         core.run();
    }
}
