package BoardModules.BasicServices;


/**
* BoardModules/BasicServices/ViewServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BoardModules.idl
* Freitag, 15. Januar 2016 20:10 Uhr MEZ
*/

public interface ViewServiceOperations 
{
  BoardModules.Message[] getAllMessageByDestination (String destination) throws BoardModules.DestinationUnreachable;
  int getState ();
} // interface ViewServiceOperations
