package Worker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aniss
 */
public class Multiplication {
    Matrix result;
    
    public Multiplication(Matrix a, Matrix b){
        Tests tests = new Tests();
        if(tests.Test_Multiplication(a, b)){
            result=new Matrix(a.getNbLines(), b.getNbColumns());
        }
    }
    
    public Matrix Mul(Matrix a, Matrix b){
        for(int i=0; i<a.getNbLines(); i++){
            for(int j=0; j<b.getNbColumns(); j++){
                result.setTabMatrix(i, j, scalaire(a,i,b,j));
            }
        }
        
        return result;
    }

    private int scalaire(Matrix a, int i, Matrix b, int j) {
        int result=0;
        int i2=0;
        int j2=0;
        while(i2<a.getNbColumns() && j2<b.getNbLines()){
            result+= a.getTabMatrix()[i][i2]*b.getTabMatrix()[j2][j];
            i2++;
            j2++;
        }
        
        return result;
    }
    
}
