/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Enumeration;
import javax.swing.JOptionPane;
import net.jxta.rendezvous.RendezVousService;

/**
 *
 * @author Abdelhakim
 */
public class Tools {
    
    
    
    
      public static void popConnectedRendezvous(RendezVousService TheRendezVous, String Name) {
        
        Enumeration e2 = TheRendezVous.getConnectedRendezVous();
       
        
        while (e2.hasMoreElements()) {
            
            

            PopInformationMessage(Name, "Connected to rendezvous:\n\n"
                    + e2.nextElement());
            
        
        
       

    }
        
    }
      
      
      
      
        public static void popConnectedPeers(RendezVousService TheRendezVous, String Name) {
        
        Enumeration e3 = TheRendezVous.getConnectedPeers();
        
        
        while (e3.hasMoreElements()) {
            
          
            
            PopInformationMessage(Name, "Peer connected to this rendezvous:\n\n"
                    + e3.nextElement().toString());
            
        }
        
       
        
    }
        
        
        
          public static void PopInformationMessage(String Name, String Message) {
        
        JOptionPane.showMessageDialog(null, Message, Name, JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public static void PopErrorMessage(String Name,String Message) {
        
        JOptionPane.showMessageDialog(null, Message, Name, JOptionPane.ERROR_MESSAGE);
        
    }
    
    public static void PopWarningMessage(String Name, String Message) {
        
        JOptionPane.showMessageDialog(null, Message, Name, JOptionPane.WARNING_MESSAGE);
        
    }
    
}
