package Master;

import Master.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Addition {
    
    Matrix result;
    

    public Addition(Matrix a, Matrix b) {
        
          Tests tests =new Tests();
          if(tests.Test_Addition(a, b)){
              result=new Matrix(a.getNbLines(), a.getNbColumns());
          }
        
    }

   public Matrix Add(Matrix a, Matrix b) {
       
       for(int i=0; i<a.getNbLines(); i++){
           for(int j=0; j<a.getNbColumns(); j++){
              
               result.setTabMatrix(i,j,a.getTabMatrix()[i][j]+b.getTabMatrix()[i][j]);
    
           }
       }
        return result;
    }
    
}
