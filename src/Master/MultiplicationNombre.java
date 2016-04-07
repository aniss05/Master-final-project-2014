package Master;

import Worker.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class MultiplicationNombre {
    Matrix result;
    
    Matrix a;
    int x;
    
    

    MultiplicationNombre(Matrix a,int x) {
        this.a=a;
        this.x=x;
        
    }
    
    public Matrix MultiplicationParNombre(Matrix a, int  x){
        result=new Matrix(a.getNbLines(), a.getNbColumns());
        for(int i=0; i<a.getNbLines(); i++){
            for(int j=0; j<a.getNbColumns(); j++){
                result.setTabMatrix(i, j, a.getTabMatrix()[i][j]*x);
            }
        }
        
        return result;
    }
    
}
