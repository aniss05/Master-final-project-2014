package Worker;


import java.util.Scanner;
import net.jxta.peergroup.PeerGroup;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Matrix {
    
    
    private double [][] tabMatrix;
    private int nbLines;
    private int nbcolumns;
    private String operation;
    public String s;
    
    
    Scanner sc =new Scanner(System.in);

    Matrix(int nbLines, int nbColumns) {
        
        this.setNbLines(nbLines);
        this.setNbColumns(nbColumns);
        this.tabMatrix=new double[nbLines][nbColumns];
        
        
        
        
    } 

    public void setNbLines(int nbLines) {
        this.nbLines=nbLines;
    }

    public void setNbColumns(int nbColumns) {
        this.nbcolumns=nbColumns;
    }

   
   
    
    
    /*public void showMatrix(String s,Matrix a){
        
        Operation op=new Operation(s, a);
        
        
        
        
    }*/
    public void showMatrix(){
      if(this.getNbLines()>1 && this.getNbColumns()>1){
            
              for(int i = 0; i < this.getNbLines(); i++){
            for(int j = 0; j < this.getNbColumns(); j++){
                if(j==0){
                    System.out.print("["+this.getTabMatrix()[i][j]+"|");
                }else{
                    if(j==this.getNbColumns()-1){
                        System.out.println(this.getTabMatrix()[i][j]+"]");
                    }else{
                        System.out.print(this.getTabMatrix()[i][j]+"|");
                    }
                }
            }
        }
            
        }else{
            if(this.getNbLines()==1 && this.getNbColumns()==1){
                System.out.println("["+this.getTabMatrix()[0][0]+"]");
                
            }else{
                if(this.getNbLines()==1 && this.getNbColumns()>1){
                    for(int j=0; j<this.getNbColumns(); j++){
                        if(j==0){
                            System.out.print("["+this.getTabMatrix()[0][j]+"|");
                            
                        }else{
                            if(j==(this.getNbColumns()-1)){
                                System.out.println(this.getTabMatrix()[0][j]+"]");
                                
                            }else{
                                System.out.print(this.getTabMatrix()[0][j]+"|");
                                
                            }
                        }
                    }
                }else{
                    if(this.getNbLines()>1 && this.getNbColumns()==1){
                          for(int i=0; i<this.getNbLines(); i++){
                             System.out.println("["+this.getTabMatrix()[i][0]+"]");
                                
                    }
                        
                    }
                }
            }
        }
        
        
        
        
    }
    
    
    public int getNbLines(){
        return this.nbLines;
    }
    
    public int getNbColumns(){
        return this.nbcolumns;
    }
    
     public void setTabMatrix(int i, int j, double val){
        this.tabMatrix[i][j] = val;
       
    }
    
    public void setTabMatrix(int a) {
        
        this.tabMatrix = tabMatrix;
    }
    
    public double [][] getTabMatrix() {
        return tabMatrix;
    }

   
}
