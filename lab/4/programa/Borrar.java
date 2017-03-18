class Borrar {

    public double calculaGamaDecimal(int iDof){

        double result = (iDof / (double) 2);
        result = result - 1;
        double multiplicatoria = 1;
        double dPI = Math.PI;

        while (result > 0) {
            multiplicatoria = multiplicatoria * result;
            result = result - 1;

        }
        //System.out.println(multiplicatoria * Math.pow(dPI, 0.5));

        return multiplicatoria * Math.pow(dPI, 0.5);
    }

    int fact(int n){

        int result;

        if(n == 1)
            return 1;

        result = fact(n-1) * n;

        return result;
    }



    public double calculaGama(int iDof){

        return (fact(iDof - 1));
    }



    public void calcula(double dX){

        dX = 1.1;
        int iNum_seg = 10;
        double dW = dX/(double) iNum_seg;
        double dE = 0.0000001;
        int iDof = 9;
        double dF0 = 0.0;
        double dF2 = 0.0;
        double dF4 = 0.0;
        double dFx = 0.0;
        double dP = (dW/3)*(dF0 + dF4 + dF2 + dFx);

        double sumatoriaf4 = 0.0;
        double sumatoriaf2 = 0.0;

        double xi = 0.0;
        double four = 0.0;

        //calcula F(0)
        dF0 = (dW/3) * (Math.pow(get3(0, iDof), -1 * ((iDof+1) / 2))) * get5(xi, iDof);
        System.out.println(dF0);


        for(int iI = 1; iI <= iNum_seg - 1 ; iI++){

            xi = dW*iI;
            //System.out.println(dW*iI);
            four = Math.pow(get3(xi, iDof), - 1 * ((iDof+1) / 2));
            //System.out.println(four);

            if(iI % 2 == 0){

                dF2 = 2 * four * get5(xi, iDof);
                dF2 = (dW / 3) * dF2;
                sumatoriaf2 = dF2 + sumatoriaf2;
                System.out.println(dF2);
                // System.out.println(2);


            } else {

                dF4 = dF4 + 4 * four * get5(xi, iDof);
                dF4 = (dW / 3) * dF4;
                sumatoriaf4 = dF4 + sumatoriaf4;
                System.out.println(dF4);
                // System.out.println(4);
            }
        }

        //calcula f(x)
        dFx = (dW/3) * (Math.pow(get3(dX, iDof), -1 * ((iDof+1) / 2))) * get5(xi, iDof);
        System.out.println(dFx);


        dP = (dF0 + sumatoriaf4 + sumatoriaf2 + dFx);
        System.out.println("Respuestas");
        System.out.println(dF0);
        System.out.println(dF2);
        System.out.println(dF4);
        System.out.println(dFx);




        System.out.println(dP);

    }

    public boolean isInt(double n){

        if(n % 1 == 0){

            return true;

        } else{

            return false;
        }
    }

    public double get3(double dX, int iDof){

        return(1 + (Math.pow(dX,2) / iDof));
    }


    public double get5(double x, int dof){

        double dPi =  Math.PI;
        double dArriba;
        double dAbajo;

        if (isInt((dof + 1) / (double) 2)){

            dArriba = calculaGama((dof + 1) / 2);
            dAbajo = Math.pow((dof * dPi) , 0.5) * calculaGamaDecimal(dof);

        } else{

            dArriba = calculaGamaDecimal((dof + 1));
            dAbajo = Math.pow((dof * dPi) , 0.5) * calculaGama(dof / 2);

        }

        //System.out.println("ARRIBA " + dArriba);
        //System.out.println("ABAJO " + dAbajo);
        //System.out.println(dArriba / dAbajo);

        return dArriba / dAbajo;
    }



    public static void main(String[] args) {

        Borrar p = new Borrar();
        p.calculaGamaDecimal(9);

        System.out.println('\n');

        p.calcula(10);


    }
}
