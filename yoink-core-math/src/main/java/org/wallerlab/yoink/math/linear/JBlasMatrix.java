package org.wallerlab.yoink.math.linear;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jblas.DoubleMatrix;
import org.wallerlab.yoink.api.service.math.Matrix;



public class JBlasMatrix implements Matrix{

	

	
	private DoubleMatrix internalMatrix;
    
	private DoubleMatrix tempMatrix;
	
	public JBlasMatrix(){
		this.internalMatrix = new DoubleMatrix();	
	}
	
	public JBlasMatrix (int rowDimension, int columnDimension) {
		this.internalMatrix = new DoubleMatrix(rowDimension, columnDimension);	
	}
	@Override
	public void setEntry(int row, int column, double value){
		this.internalMatrix.put(row,column, value);
	}
	@Override
	public double getEntry(int row, int column){
		return this.internalMatrix.get(row, column);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Matrix transpose(){
		tempMatrix=this.internalMatrix.transpose();
		Matrix newMatrix = new JBlasMatrix();
		newMatrix.setInternalMatrix(tempMatrix);
		return newMatrix;
	}
	@Override
	public Matrix add(Matrix m){
		tempMatrix= this.internalMatrix.addi((DoubleMatrix) m.getInternalMatrix());
		Matrix newMatrix = new JBlasMatrix();
		newMatrix.setInternalMatrix(tempMatrix);
		return newMatrix;
	}
	@Override
	public Matrix subtract (Matrix m){
		tempMatrix = this.internalMatrix.subi((DoubleMatrix)m.getInternalMatrix());
		Matrix newMatrix = (Matrix) new JBlasMatrix();
		newMatrix.setInternalMatrix(tempMatrix);
		return newMatrix;	
	}	
	@Override
	public Matrix scalarMultiply(double d) {
		for (int i=0;i<internalMatrix.getRows();i++){
			tempMatrix = this.internalMatrix.mulRow(i, d);
		}
		Matrix newMatrix = (Matrix) new JBlasMatrix();
		newMatrix.setInternalMatrix(tempMatrix);
		return newMatrix;
	}
	@Override
	public double dotProduct() {
		DoubleMatrix vector = this.internalMatrix.getRow(0);
		return vector.dot(vector);
	}
	@Override
	public double[][] getData() {
		double [][] array = new double [this.internalMatrix.getRows()][this.internalMatrix.getColumns()];
		for (int i = 0; i< internalMatrix.getRows();i++){
			for (int j = 0; j< internalMatrix.getColumns();j++){
				array [i][j]= this.internalMatrix.get(i,j);
			}
		}
		return array ;
	}
	
	@Override
	public double [] getRow(int i) {
		double [] array = new double [this.internalMatrix.getColumns()];
		for (int j=0; j<this.internalMatrix.getColumns();j++){
			array[j] = this.internalMatrix.get(i,j);		
		}
		return array ;
	}
	
	public DoubleMatrix getInternalMatrix() {
		return internalMatrix; 
		
	}
	@Override
	public Matrix ebeMultiply (Matrix m){
		DoubleMatrix vector1 = this.internalMatrix.getRow(0);
		DoubleMatrix vector2 = ((DoubleMatrix) m.getInternalMatrix()).getRow(0);
		DoubleMatrix vectorEBE = vector1.mul(vector2);
		tempMatrix = new DoubleMatrix(1,3);
	    tempMatrix.putRow(0, vectorEBE);
		Matrix newMatrix = new JBlasMatrix();
		newMatrix.setInternalMatrix(tempMatrix);
		return newMatrix;
	}
	@Override
	public double distance(Matrix m) {
		DoubleMatrix vector1 = this.internalMatrix.getColumn(0);
		DoubleMatrix vector2 = ((DoubleMatrix) m.getInternalMatrix()).getColumn(0);
		return vector1.distance2(vector2);
	}
	@Override
	//nochmal gucken ob das hier richtig ist !!!! 
	public double getNorm() {
		DoubleMatrix vector1 = this.internalMatrix.getRow(0);
		return vector1.norm1();
	}
	@Override
	public boolean equals(Matrix m) {
		return this.internalMatrix.equals(m.getInternalMatrix());

	}
	@Override
	public void array2DRowRealMatrix(double[][] d) {
	     throw new UnsupportedOperationException();
	}
	@Override
	public void addToEntry(int row, int column, double value) {
		throw new UnsupportedOperationException();

    }
	// Carefull this Cast my not work maybe try and exception
	public void setInternalMatrix(Object internalMatrix) {
		this.internalMatrix = (DoubleMatrix ) internalMatrix;
	
	}

}
