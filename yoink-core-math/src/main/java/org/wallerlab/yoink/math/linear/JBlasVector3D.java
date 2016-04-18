package org.wallerlab.yoink.math.linear;



import org.jblas.DoubleMatrix;
import org.wallerlab.yoink.api.service.math.Vector;




public class JBlasVector3D implements Vector<DoubleMatrix>{
 
	private DoubleMatrix internalVector;
	private DoubleMatrix tempVector;
	
	public JBlasVector3D (double x, double y, double z){
		double [][] a = new double [3] [1];// {{x}, {y}, {z}};
		a[0][0] = x;
		a[1][0] = y;
		a[2][0] = z;
		this.internalVector= new DoubleMatrix(a);
	}
	public JBlasVector3D (){	
	}
	public JBlasVector3D (double[] d){
		 double[][] newarray = new double [3][1];
		  for (int i = 0; i < d.length; ++i) {
		     for (int j = 0; j < 1; ++j) {
		         newarray[i][j]=d[i];
		     }
		  }
		 this.internalVector = new DoubleMatrix(newarray);
	}
	@Override
	public double dotProduct() {
		return this.internalVector.dot(this.internalVector);
	}
	@Override
	public double getNorm() {
		return this.internalVector.norm2();
	}
	@Override
	public Vector add(Vector m) {
		tempVector = this.internalVector.add((DoubleMatrix) m.getInternalVector());
		Vector temp = new JBlasVector3D();
		temp.setInternalVector(tempVector);
		return temp;
	}
	@Override
	public Vector subtract(Vector m) {
		tempVector = this.internalVector.sub((DoubleMatrix) m.getInternalVector());
		Vector temp = new JBlasVector3D();
		temp.setInternalVector(tempVector);
		return temp;
	}
	@Override
	public Vector scalarMultiply(double d) {
		Vector temp = new JBlasVector3D();
		for (int i=0; i<internalVector.getLength();i++)
		temp.setInternalVector(this.internalVector.mulColumn(i,d));
		return temp;
	}
	@Override
	public double getX() {
		return this.internalVector.get(0,0);
	}
	@Override
	public double getY() {
		return this.internalVector.get(1,0);
	}
	@Override
    public double getZ() {
	    return this.internalVector.get(2,0);
    }
	@Override
    public double distance(Vector m) {
		return this.internalVector.distance2((DoubleMatrix) m.getInternalVector());
	}
   @Override   
    public Vector ebeMultiply(Vector m) {
		DoubleMatrix tempM =   (DoubleMatrix) m.getInternalVector();
		double x =   tempM.get(0,0) * this.internalVector.get(0,0);
		double y =   tempM.get(1,0) * this.internalVector.get(1,0);
		double z =   tempM.get(2,0) * this.internalVector.get(2,0);
		double [][] array = new double [3][1];
		array[0][0] = x;
		array[1][0] = y;
		array[2][0] = z;
		tempVector = new DoubleMatrix(array);
		Vector temp = new JBlasVector3D();
		temp.setInternalVector(tempVector);
		return temp;
	}
	@Override
    public double getEntry(int i) {
		double element = 0;
		switch (i) {
		case 0:
			element = this.internalVector.get(0,0);
			break;
		case 1:
			element = this.internalVector.get(1,0);
			break;
		case 2:
			element = this.internalVector.get(2,0);
			break;
		}
		return element;
	}
	@Override
	public DoubleMatrix getInternalVector() {
		return this.internalVector;
	}
	@Override
	public void setInternalVector(DoubleMatrix internalVector) {
		this.internalVector =  internalVector;
	}
	@Override
	public boolean equals(Vector m) {
		return this.internalVector.equals(m.getInternalVector());
	}
	@Override
	public double[] toArray() {
		return this.internalVector.toArray();
	}
	@Override
	public Vector exp() {
		double x = Math.exp(this.internalVector.get(0,0));
		double y = Math.exp(this.internalVector.get(1,0));
		double z = Math.exp(this.internalVector.get(2,0));
		tempVector = new DoubleMatrix();
		Vector temp = new JBlasVector3D();
		temp.setInternalVector(tempVector);
		return temp;

	}

}
