/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idls.BoardModules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import BoardModules.User;


/**
 *
 * @author Carsten
 */
public class CreateUser 
{
 public void Aufrufe() 
 {
     String userName = "";
     userName = Eingabe(userName);
     userErstellen(userName);
 }
    
private String Eingabe(String name) 
 {
    // TODO code application logic here
    String eingabeName = "";
    
    try 
    {
        BufferedReader input = new BufferedReader (
    new InputStreamReader (System.in));
        System.out.println("Bitte geben sie einen Benutzernamen ein:");
        eingabeName = input.readLine(); 
    } 
    catch
    (IOException e) 
    {
        System.err.println(e.toString());
    }   
    return (eingabeName);
 
 }
 
 private User userErstellen(String Name) 
 {
    User user = new User(Name);
    return (user);
 }
}