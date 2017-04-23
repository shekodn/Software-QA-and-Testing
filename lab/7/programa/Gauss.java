import java.util.*;


public class Gauss {

    public double b0 = 0.0;
    public double b1 = 0.0;
    public double b2 = 0.0;
    public double b3 = 0.0;

    public double getB0(){
        return b0;
    }
    public double getB1(){
        return b1;
    }
    public double getB2(){
        return b2;
    }
    public double getB3(){
        return b3;
    }

    public void calcula(double[][] A, double[] B, int iN) {

        iN = 4;

        for (int k = 0; k < iN; k++) {
            /** find pivot row **/
            int max = k;

            for (int i = k + 1; i < iN; i++){
                if (Math.abs(A[i][k]) > Math.abs(A[max][k])){
                    max = i;
                }
            }

            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            for (int i = k + 1; i < iN; i++){
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];

                for (int j = k; j < iN; j++){
                    A[i][j] -= factor * A[k][j];
                }
            }
        }

        //sustitucion hacia atras
        double[] solution = new double[iN];

        for (int i = iN - 1; i >= 0; i--){
            double sum = 0.0;

            for (int j = i + 1; j < iN; j++){
                sum += A[i][j] * solution[j];
            }

            solution[i] = (B[i] - sum) / A[i][i];
        }

        b0 = solution[0];
        b1 = solution[1];
        b2 = solution[2];
        b3 = solution[3];
    }

    public void printRowEchelonForm(double[][] A, double[] B){
        int iN = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < iN; i++)
           {
               for (int j = 0; j < iN; j++)
                   System.out.printf("%.3f ", A[i][j] + '\t');
               System.out.printf("| %.3f\n", B[i]);
           }
        System.out.println();
    }
}
