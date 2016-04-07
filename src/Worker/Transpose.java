package Worker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Transpose {
    public Matrix result;
    public Transpose(Matrix a){
        
        
        Tests tests = new Tests();
        if(tests.Test_Transpose(a)){
            result=new Matrix(a.getNbColumns(), a.getNbLines());
        }
        
    }
    
    public Matrix transpose(Matrix a){
        for(int i=0; i<result.getNbLines(); i++){
            for(int j=0; j<result.getNbColumns(); j++){
                result.setTabMatrix(i, j, a.getTabMatrix()[j][i]);
            }
        }
        return result;
        
    }
    
}
