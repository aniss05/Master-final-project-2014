package Worker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Soustraction {
    
    Matrix result;
    

    public Soustraction(Matrix a, Matrix b) {
        
          Tests tests =new Tests();
          if(tests.Test_Soustraction(a, b)){
              result=new Matrix(a.getNbLines(), a.getNbColumns());
          }
        
    }

   public Matrix Sous(Matrix a, Matrix b) {
       
       for(int i=0; i<a.getNbLines(); i++){
           for(int j=0; j<a.getNbColumns(); j++){
              
               result.setTabMatrix(i,j,a.getTabMatrix()[i][j]-b.getTabMatrix()[i][j]);
    
           }
       }
        return result;
    }
    
}
