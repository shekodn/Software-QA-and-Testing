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

        for (int iK = 0; iK < iN; iK++) {

            int iMax = iK;

            for (int iI = iK + 1; iI < iN; iI++){
                if (Math.abs(A[iI][iK]) > Math.abs(A[iMax][iK])){
                    iMax = iI;
                }
            }

            //intercambio
            double[] aux = A[iK];
            A[iK] = A[iMax];
            A[iMax] = aux;
            double t = B[iK];
            B[iK] = B[iMax];
            B[iMax] = t;

            for (int iI = iK + 1; iI < iN; iI++){
                double factor = A[iI][iK] / A[iK][iK];
                B[iI] -= factor * B[iK];

                for (int iJ = iK; iJ < iN; iJ++){
                    A[iI][iJ] -= factor * A[iK][iJ];
                }
            }
        }

        //sustitucion hacia atras
        double[] solution = new double[iN];

        for (int iI = iN - 1; iI >= 0; iI--){
            double sum = 0.0;

            for (int iJ = iI + 1; iJ < iN; iJ++){
                sum += A[iI][iJ] * solution[iJ];
            }

            solution[iI] = (B[iI] - sum) / A[iI][iI];
        }

        b0 = solution[0];
        b1 = solution[1];
        b2 = solution[2];
        b3 = solution[3];
    }

    public void printRowEchelonForm(double[][] A, double[] B){
        int iN = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int iI = 0; iI < iN; iI++)
           {
               for (int iJ = 0; iJ < iN; iJ++)
                   System.out.printf("%.3f ", A[iI][iJ] + '\t');
               System.out.printf("| %.3f\n", B[iI]);
           }
        System.out.println();
    }
}
