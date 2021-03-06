package BoardModules.AdvancedServices;


/**
* BoardModules/AdvancedServices/VirtualGroupServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idls/BoardModules.idl
* Donnerstag, 14. Januar 2016 14:02 Uhr MEZ
*/

public interface VirtualGroupServiceOperations 
{
  void addMember (String boardname, BoardModules.User[] users);
  void removeMember (String boardname);
  void createBackupOfVirtualGroup (BoardModules.StringListHolder membernames, BoardModules.UserListHolder users, BoardModules.MessageListHolder messages);
  void heartbeat ();
} // interface VirtualGroupServiceOperations
