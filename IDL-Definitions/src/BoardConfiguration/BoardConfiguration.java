/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardConfiguration;

/**
 *
 * @author Tobias
 */
public class BoardConfiguration {
    public static final String ORB_PORT = "1050";
    
    public static final String NAMESERVICE = "NameService";
    
    public static final String ADMIN_SERVICE_NAME = "AdminService";
    public static final String BOARD_SERVICE_NAME = "BoardService";
    public static final String VIEW_SERVICE_NAME = "ViewService";
    public static final String VGROUP_SERVICE_NAME = "VirtualGroupService";
    
    /*
     * Pfade zum Speichern von Usern und Messages 
     */
    public static final String COMMON_STORAGE_PATH = ".\\";
    public static final String USER_STORAGE_PATH = "_User.xml";
    public static final String MESSAGE_STORAGE_PATH = "_Message.xml";
}
