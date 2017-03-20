//&p-Simpson
public class Simpson {
	private double X;
	private int DOF;
	private int num_seg;
	private double W;

	//&i
	public Simpson(double x, int dof, int num){
		X = x;
		DOF = dof;
		num_seg = num;
		W = x / num_seg;
	}

	//&i
	public double getP(){
		double Xi = 0;
		int mult;
		double res = 0;
		DistT distT;

		for (int i=0; i<=num_seg; i++){
			distT = new DistT(Xi, DOF);

			if (i==0 || i==num_seg){
				mult = 1;
			}
			else if (i%2 == 0){
				mult = 2;
			}
			else {
				mult = 4;
			}

			res += ( (W/3) * mult * distT.calculaF() );

			Xi += W;
		}

		return res;
	}
}
