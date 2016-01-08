/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import BasicServices.BoardServiceImpl;

/**
 *
 * @author Carsten
 */
public class EnterUser
{
  public void userAbfrage() 
 {
    // TODO code application logic here
    String eingabeUser = "";
    String name = "";
    String user = "";
    eingabeUser = Eingabe(name);
    user = eingabeUser;
    //checkUser(user);
    if (user == eingabeUser)
    {
        Ausgabe(name);
    }
    else
    {
        Fehler(name);
    }    
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

    private void Ausgabe(String name) 
    {
        System.out.println("Der Benutzer" +name+ " ist bereits angemeldet");
    }

    private void Fehler(String name) 
    {        
        System.out.println("Der Benutzer" +name+ " ist noch nicht angemeldet");   
    }
}
