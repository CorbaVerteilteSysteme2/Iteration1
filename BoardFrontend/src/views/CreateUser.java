/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Carsten
 */
public class CreateUser 
{
    public void userErstellen() 
    {
    // TODO code application logic here
    String name = "";
    String eingabeUser = Eingabe(name);
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
}
