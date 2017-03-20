//&p-DistT
public class DistT {

	private double dXi = 0;
	private int iDof = 0;

	//&i
	public DistT(double iX, int iDof){
		this.dXi = iX;
		this. iDof = iDof;
	}

	//&i
	public double calculaGama(double x){
		if (x == 0){
			return -1;
		}
		if (x == 1.0){
			return 1;
		}
		if (x == 0.5){

			return Math.sqrt(Math.PI);
		}
		else{
			return (x - 1) * calculaGama(x - 1);
		}
	}


	//&i
	public double calculaF(){

		double dAnswer = 0;
		double aux;
		double dX;
		double dY;
		double dZ;

		dX = 1.0 * ((iDof + 1) * 0.5);
		aux = calculaGama(dX);

		dY = Math.pow(iDof * Math.PI, 0.5);

		dX = 1.0 * (iDof * 0.5);
		dZ = calculaGama(dX);

		dAnswer = aux / (dY * dZ);
		//System.out.println(dAnswer);

		dX = 1.0 + (Math.pow(dXi, 2)/iDof);
		dY = -1.0 * ((iDof + 1) * 0.5);

		aux = Math.pow(dX, dY);
		dAnswer = dAnswer * aux;

		//System.out.println(dAnswer);
		return dAnswer;
	}
}
