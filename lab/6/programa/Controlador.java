//&p-Controlador
//&b=154
import java.io.*;
import java.util.*;

public class Controlador {

    private double dX2;
    private double dY2;
    private double dXY;
    private double dXavg;
    private double dYavg;
    private double dXi;
    private double dYi;
    private double dB1;
    private double dB0;
    private double dR;
    private double dR2;
    private double dYK;
    private int dXK;
    private int iN;

    //p6
    protected double dSig;
    protected double dUPI;
    protected double dLPI;
    protected double dT;
    protected double dStdDev;
    protected double dRange;
    protected double XiMinusXavg;


    /* Getters */
    public double getX2(){
        return dX2;
    }
    public double getY2(){
        return dY2;
    }
    public double getXY(){
        return dXY;
    }
    public double getXavg(){
        return dXavg;
    }
    public double getYavg(){
        return dYavg;
    }
    public double getXi(){
        return dXi;
    }
    public double getYi(){
        return dYi;
    }
    public double getB1(){
        return dB1;
    }
    public double getB0(){
        return dB0;
    }
    public double getR(){
        return  dR;
    }
    public double getR2(){
        return  dR2;
    }
    public double getYK(){
        return  dYK;
    }
    public int getXK(){
        return dXK;
    }
    public double getiN(){
        return iN;
    }

    /* Setters */

    public void setX2(double dNum){
        this.dX2 = dNum;
    }

    public void setY2(double dNum){
        this.dY2 = dNum;
    }

    public void setXY(double dNum){
        this.dXY = dNum;
    }

    public void setXavg(double dNum){
        this.dXavg = dNum;
    }

    public void setYavg(double dNum){
        this.dYavg = dNum;
    }

    public void setXi(double dNum){
        this.dXi = dNum;
    }

    public void setYi(double dNum){
        this.dYi = dNum;
    }

    public void setB1(double dNum){
        this.dB1 = dNum;
    }

    public void setB0(double dNum){
        this.dB0 = dNum;
    }

    public void setR(double dNum){
        this.dR = dNum;
    }

    public void setR2(double dNum){
        this.dR2 = dNum;
    }

    public void setYK(double dNum){
        this.dYK = dNum;
    }

    public void setXK(int dNum){
        this.dXK = dNum;
    }

    public void setiN(int iNum){
        this.iN = iNum;
    }

    /* methods */

    /**
     * [rounds to the fifth decimal place]
     * @param  double dNum          [description]
     * @return        [description]
     */
    //&i
    public double roundNumber(double dNum){

        double factor = 1e5; // = 1 * 10^5 = 100000.
        double result = Math.round(dNum * factor) / factor;

        return result;
    }

    //&i
    public void printInfo(){

        System.out.println("N  = " + iN );
        System.out.println("xk = " + dXK);
        System.out.println("r  = " + roundNumber(dR));
        System.out.println("r2 = " + roundNumber(dR2));
        System.out.println("b0 = " + roundNumber(dB0));
        System.out.println("b1 = " + roundNumber(dB1));
        System.out.println("yk = " + roundNumber(dYK));
        System.out.printf("sig = %.10f\n", dSig); //&m
        System.out.printf("ran = %.05f\n", dRange); //&m
        System.out.printf("LS = %.05f\n", dUPI); //&m
        System.out.printf("LI = %.05f\n", dLPI); //&m



    }

    /* Sumatorias */

    ////////////////////////////////////////////////////////////////////////////

    //&i
    public double calculaCuadrado(double dNum){
        return dNum * dNum;
    }

    //&i
    public double sumatoriaX2(LinkedList <Coordenada> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI).getX();
            dN = calculaCuadrado(dN);
            dSum = dSum + dN;
        }

        return dSum;
    }

    //&i
    public double sumatoriaY2(LinkedList <Coordenada> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dN = list.get(iI).getY();;
            dN = calculaCuadrado(dN);
            dSum = dSum + dN;
        }

        return dSum;
    }

    ////////////////////////////////////////////////////////////////////////////

    //&i
    public double calculaXY(double dX, double dY){
        return (dX) * (dY);
    }

    //&i
    public double sumatoriaXY(LinkedList <Coordenada> list){

        double dSum = 0;

        for(int iI = 0; iI < list.size(); iI++){

            double dX = list.get(iI).getX();;
            double dY = list.get(iI).getY();;
            double dN = calculaXY(dX, dY);

            dSum = dSum + dN;
        }

        return dSum;
    }

    ////////////////////////////////////////////////////////////////////////////

    //&i
    public double calculaPromedio(double dNum, int size){
        return ((dNum) / size);
    }

    //&i
    public double sumatoriaXi(LinkedList <Coordenada> list){

        double dSum = 0;
        double dN = 0;

        for(int iI = 0; iI < list.size(); iI++){

            dN = (list.get(iI).getX());
            dSum = dSum + dN;
        }

        return (dSum);
    }

    //&i
    public double sumatoriaYi(LinkedList <Coordenada> list){

        double dSum = 0;
        double dN = 0;


        for(int iI = 0; iI < list.size(); iI++){

            dN = (list.get(iI).getY());
            dSum = dSum + dN;
        }

        return (dSum);
    }

    //&i
    public double calculaB1(LinkedList <Coordenada> list){

        double xiyi = (sumatoriaXY(list));
        double n = list.size();
        double xavg = calculaPromedio(sumatoriaXi(list), list.size());
        double yavg = calculaPromedio(sumatoriaYi(list), list.size());
        double x2 = (sumatoriaX2(list));

        return ((xiyi) - (n*xavg*yavg)) / ((x2) - (n * (xavg * xavg )));
    }

    ////////////////////////////////////////////////////////////////////////////

    //&i
    public double calculaR(LinkedList <Coordenada> list){

        double xiyi = sumatoriaXY(list);
        double n = list.size();
        double xi = sumatoriaXi(list);
        double yi = sumatoriaYi(list);
        double xavg = calculaPromedio(sumatoriaXi(list), list.size());
        double yavg = calculaPromedio(sumatoriaYi(list), list.size());
        double x2 = sumatoriaX2(list);
        double y2 = sumatoriaY2(list);

        return ( (n*(xiyi) - (xi)*(yi)) / Math.sqrt( ((n * (x2)-(xi * xi)) * (n * (y2) - (yi * yi)))));
    }

    //&i
    public double calculaR2(double dR){

        return ((dR) * (dR));
    }

    //&i
    public double calculaB0(LinkedList <Coordenada> list){
        double yavg = (calculaPromedio(sumatoriaYi(list), list.size()));
        double b1 = (calculaB1(list));
        double xavg = (calculaPromedio(sumatoriaXi(list), list.size()));

        return (yavg - ((b1 * xavg)));
    }

    //&i
    public double calculaYK(LinkedList <Coordenada> list, double xk){

        double b1 = (calculaB1(list));
        double b0 = (calculaB0(list));
        return (b0 + (b1 * xk));
    }


    ////////////////////////////////////////////////////////////////////////////

    //p6
    //&i
    public double calculaX(double dR, int iN){ //&m
        //System.out.println((Math.abs(dR)*Math.sqrt(iN - 2))/ Math.sqrt(1 - (dR * dR)));
        return (Math.abs(dR)*Math.sqrt(iN - 2))/Math.sqrt(1.0 - (dR * dR));
    }

    //&i
    public double sumatoriaXiMinusXavg(LinkedList <Coordenada> list){

        double dSum = 0.0;
        double xiaux = sumatoriaXi(list);
        double dXavg = xiaux/list.size();


        for(int iI = 0; iI < list.size(); iI++){

            dXi = (list.get(iI).getX());

            dSum = dSum + calculaCuadrado(dXi - dXavg);
        }

        return dSum;
    }

    //&i
    public double sumatoriaStdDec(LinkedList <Coordenada> list, double b0, double b1){

        double dSum = 0.0;
        double yi = 0.0;
        double xi = 0.0;
        double operacion = 0.0;

        for(int iI = 0; iI < list.size(); iI++){

            yi = (list.get(iI).getY());
            xi = (list.get(iI).getX());
            operacion = yi - b0 - (b1*xi);
            dSum = dSum + calculaCuadrado(operacion);
        }

        return (dSum);
    }

    //&i
    public double calculaStdDev(LinkedList <Coordenada> list, double b0, double b1){

        double der = sumatoriaStdDec(list, b0, b1);
        double izq = 1.0 / (list.size() - 2.0);

        return Math.sqrt(izq * der);
    }

    //&i
    public double calculaRange(double dTDist, double dStdDev, int dXK, double dXavg, double iN, double sumatoria){

        return dTDist*dStdDev*Math.sqrt(1.0 + (1.0/iN) + (calculaCuadrado(dXK - dXavg) / sumatoria));
    }


}
