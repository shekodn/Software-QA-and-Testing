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



    public double calcula(double dX, int iDof, int iNum_seg){

        double dW = dX / (double) iNum_seg;
        double dE = 0.0000001;
        double dF0 = 0.0;
        double dF2 = 0.0;
        double dF4 = 0.0;
        double dFx = 0.0;
        double dP = 0.0;

        double sumatoriaf4 = 0.0;
        double sumatoriaf2 = 0.0;

        double xi = 0.0;
        double four = 0.0;

        //calcula F(0)
        //dF0 = (dW/3) * (Math.pow(get3(0, iDof), -1 * ((iDof+1) / 2))) * get5(xi, iDof);
        dF0 = (Math.pow(get3(0, iDof), (-1) * ((iDof+1) / 2))) * get5(xi, iDof);
        // System.out.println("Df0 " + dF0);
        //System.out.println("0 => " + ((dW/3) * dF0));

        for(int iI = 1; iI <= iNum_seg - 1 ; iI++){

            xi = dW * iI;
            //System.out.println(dW*iI);
            four = Math.pow(get3(xi, iDof), - 1 * ((iDof+1) / 2));
            //System.out.println(four);

            if(iI % 2 == 0){

                dF2 = 2 * four * get5(xi, iDof);
                sumatoriaf2 = dF2 + sumatoriaf2;
                // System.out.println("2 => " + ((dW/3) * dF2));

            } else {

                dF4 = 4 * four * get5(xi, iDof);
                sumatoriaf4 = dF4 + sumatoriaf4;
                // System.out.println("4 => " + (dW/3) * dF4);
                //System.out.println(dF4);
                // System.out.println(4);
            }
        }


        //System.out.println("Xi " + dW);
        //calcula f(x)
        dFx = (Math.pow(get3(dX, iDof), -1 * ((iDof+1) / 2))) * get5(dW, iDof);
        //System.out.println("X => " + ((dW/3) * dFx));

        dP = (dW/3) * (dF0 + sumatoriaf4 + sumatoriaf2 + dFx);

        System.out.println(dP);

        return dP;

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
        //System.out.println("a/b " + dArriba / dAbajo);

        return dArriba / dAbajo;
    }

    public double calculaNewton(double x, int dof, int num_seg){

        double eps = 0.0001;

        double primero = calcula(x, dof, num_seg);
        double segundo = calcula(x, dof, num_seg * 2);

        while(Math.abs(primero - segundo) > eps){

            num_seg = num_seg * 2;
            primero = calcula(x, dof, num_seg);
            segundo = calcula(x, dof, num_seg * 2);

            primero = segundo;

            System.out.println(num_seg);
        }


        return segundo;

    }

////////////////////////////////////////////////////////////////////////////////

    public double gama(float x){ // Conseguir el gama al recibir un valor real
        if(x==1){
            return 1;
        }else if(x==-0.5){
            return Math.sqrt(Math.PI);
        }else{
            return x*gama(x-1);
        }
    }


    // public double CalculaP(){ // Formula completa para conseguir el resultado de P
    //     double total=0;
    //
    //     double dof = 10;
    //     double num_seg = 10;
    //     double dW =
    //
    //     //cout << "hola" << endl;
    //     double ladoIzquierdo = gama(((dof + 1)/2.0) -1 ) / (Math.sqrt(Math.PI()) * gama(dof/2.0)-1));
    //
    //     for(int iA = 0; iA <= num_seg; iA++){
    //         if(iA == 0 || iA == 10){
    //             total += ladoIzquierdo * Math.pow((1+(Data.getw()*iA)*(Data.getw()*iA)/Data.getdof()),-(Data.getdof()+1)/2.0);
    //         }else if(iA%2==0){
    //             total += 2*ladoIzquierdo*pow((1+(Data.getw()*iA)*(Data.getw()*iA)/Data.getdof()),-(Data.getdof()+1)/2.0);
    //         }else{
    //             total += 4*ladoIzquierdo*pow((1+(Data.getw()*iA)*(Data.getw()*iA)/Data.getdof()),-(Data.getdof()+1)/2.0);
    //         }
    //     }
    //     //cout << total << endl;
    //     return Data.getw()*total/3.0;
    // }


    public static void main(String[] args) {

        Borrar p = new Borrar();

        //p.calcula(1.1, 9, 10) ;
        //p.calcula(1.1812, 10);
        //p.calcula(2.75, 30, 10);
        System.out.println(p.calculaNewton(1.1,9,10));
        //System.out.println(p.calculaNewton(2.75, 30, 10));
        System.out.println(p.calculaNewton(1.1812, 10, 10));
    }
}
