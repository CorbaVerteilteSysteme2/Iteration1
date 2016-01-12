/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public interface IVirtualGroupMembershipStorage {
    /*
        Diese Methode soll, wenn man Mitglied einer virtuellen Gruppe wird, dies in einer Datei
        speichern. Wenn diese Datei nicht existiert, soll sie angelegt werden.
    */
    void storeMembership(String vgroupname);
    
    /*
        Diese Methode soll aus einer Datei die virtuellen Gruppen auslesen. 
    */
    ArrayList<String> loadMembership();
}
