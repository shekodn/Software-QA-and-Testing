//&p-Simpson
public class Simpson {

	//&i
	private double dX;
	private int iDof;
	private int num_seg;
	private double dW;

	//&i
	public Simpson(double dX, int iDof, int iNumberOfSegments){
		this.dX = dX;
		this.iDof = iDof;
		this.num_seg = iNumberOfSegments;
		this.dW = dX / iNumberOfSegments;
	}

	//&i
	public double calculaP(){
		double dXi = 0;
		int iMultiplier;
		double dAnswer = 0;
		DistT distT;

		for (int iI = 0; iI <= num_seg; iI++){

			distT = new DistT(dXi, iDof);

			if (iI == 0 || iI == num_seg){
			 iMultiplier = 1;
			} else if (iI % 2 == 0){
			 iMultiplier = 2;
		 	} else {
			 iMultiplier = 4;
			}

			dAnswer = dAnswer + ((dW/3) * iMultiplier * distT.calculaF());

			dXi = dXi + dW;
		}

		return dAnswer;
	}
}
