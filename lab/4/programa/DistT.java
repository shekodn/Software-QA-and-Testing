//&p-DistT
public class DistT {
	private double Xi = 0;
	private int DOF = 0;

	//&i
	public DistT(double x, int dof){
		Xi = x;
		DOF = dof;
	}

	//&i
	public static double Gamma(double x){
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
			return (x-1)*Gamma(x-1);
		}
	}

	//&i
	public double calculaF(){
		double res = 0;
		double x,y,z,aux;
		x = 1.0 * ((DOF + 1)*0.5);
		aux = Gamma(x);

		y = Math.pow(DOF * Math.PI, 0.5);

		x = 1.0 * (DOF*0.5);
		z = Gamma(x);

		res = aux / (y *z);

		x = 1.0 + (Math.pow(Xi, 2)/DOF);
		y = -1.0 * ((DOF + 1) * 0.5);

		aux = Math.pow(x, y);
		res = res * aux;

		return res;
	}
}
