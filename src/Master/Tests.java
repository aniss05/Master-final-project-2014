package Master;


import Worker.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Tests extends JFrame{
    
   
    
    
    
  public boolean Test_Addition(Matrix a, Matrix b){
        if((a.getNbLines()==b.getNbLines())&&(a.getNbColumns()==b.getNbColumns())){
             
            return true;
            
        }else{
            
            JOptionPane.showMessageDialog(this, "Mauvaise taille","Addition impossible",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    
     public boolean Test_Soustraction(Matrix a, Matrix b){
         if((a.getNbLines()==b.getNbLines())&&(a.getNbColumns()==b.getNbColumns())){
             
            return true;
            
        }else{
            
            JOptionPane.showMessageDialog(this, "Mauvaise taille","Soustraction impossible",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
     
     public boolean Test_Multiplication(Matrix a, Matrix b){
         
         if(a.getNbColumns()==b.getNbLines()){
             return true;
         }else{
              JOptionPane.showMessageDialog(this, "Mauvaise taille","Multiplication impossible",JOptionPane.ERROR_MESSAGE);
            return false;
         }
     }
     
     
      public boolean Test_Division(Matrix a, Matrix b){
        if((a.getNbLines()==b.getNbLines())&&(a.getNbColumns()==b.getNbColumns())){
             
            return true;
            
        }else{
            JOptionPane.showMessageDialog(this, "Mauvaise taille","Division impossible",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
      
      public boolean Test_Transpose(Matrix a){
          if(a.getNbLines()>0 && a.getNbColumns()>0){
              return true;
          }else{
               JOptionPane.showMessageDialog(this, "Mauvaise taille","Transpose impossible",JOptionPane.ERROR_MESSAGE);
            return false;
          }
      }
    
}
