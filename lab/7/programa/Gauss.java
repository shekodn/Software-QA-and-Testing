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

    public void calcula(double[][] coeficientes, double[] resultados, int iN) {

        iN = 4;

        for (int iK = 0; iK < iN; iK++) {

            int iMax = iK;

            for (int iI = iK + 1; iI < iN; iI++){
                if (Math.abs(coeficientes[iI][iK]) > Math.abs(coeficientes[iMax][iK])){
                    iMax = iI;
                }
            }

            //intercambio
            double[] aux = coeficientes[iK];
            coeficientes[iK] = coeficientes[iMax];
            coeficientes[iMax] = aux;
            double t = resultados[iK];
            resultados[iK] = resultados[iMax];
            resultados[iMax] = t;

            for (int iI = iK + 1; iI < iN; iI++){
                double factor = coeficientes[iI][iK] / coeficientes[iK][iK];
                resultados[iI] -= factor * resultados[iK];

                for (int iJ = iK; iJ < iN; iJ++){
                    coeficientes[iI][iJ] -= factor * coeficientes[iK][iJ];
                }
            }
        }

        //sustitucion hacia atras
        double[] solution = new double[iN];

        for (int iI = iN - 1; iI >= 0; iI--){
            double sum = 0.0;

            for (int iJ = iI + 1; iJ < iN; iJ++){
                sum += coeficientes[iI][iJ] * solution[iJ];
            }

            solution[iI] = (resultados[iI] - sum) / coeficientes[iI][iI];
        }

        b0 = solution[0];
        b1 = solution[1];
        b2 = solution[2];
        b3 = solution[3];
    }

    public void printRowEchelonForm(double[][] coeficientes, double[] resultados){
        int iN = resultados.length;
        System.out.println("\nRow Echelon form : ");
        for (int iI = 0; iI < iN; iI++)
           {
               for (int iJ = 0; iJ < iN; iJ++)
                   System.out.printf("%.3f ", coeficientes[iI][iJ] + '\t');
               System.out.printf("| %.3f\n", resultados[iI]);
           }
        System.out.println();
    }
}
