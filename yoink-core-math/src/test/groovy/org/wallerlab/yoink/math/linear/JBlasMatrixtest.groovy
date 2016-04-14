package org.wallerlab.yoink.math.linear

import org.jblas.DoubleMatrix
import org.wallerlab.yoink.api.service.math.Matrix;
import spock.lang.Specification



class JBlasMatrixtest extends Specification {
	
	def"internalMatrix is a DoubleMatrix"(){
		when:
		DoubleMatrix internalMatrix = new DoubleMatrix()
		then:
		assert internalMatrix.class == DoubleMatrix
	}
	
	def"tempMatrix is a DoubleMatrix"(){
		when:
		DoubleMatrix tempMatrix = new DoubleMatrix()
		then:
		assert tempMatrix.class == DoubleMatrix
	}

	def "create a matrix, row and column both are positice integer"(){
		when:
		def matrix = new DoubleMatrix(1,3)		
		then:
		matrix.getColumns()==3
		matrix.getRows()==1
	}

	def"change(set/get/add) the value of a element in matrix"(){
		when:
		Matrix matrix= new JBlasMatrix(1,3)
		then:
		matrix.setEntry(2,0,25)
		matrix.getEntry(2, 0)==25
	}
	
	def"test mehtod transpose"(){
		when:
		Matrix matrix= new JBlasMatrix(2,2)
		matrix.setEntry(0, 1, 1)
		Matrix resultMatrix= new JBlasMatrix (2,2)
		resultMatrix.setEntry(1, 0, 1)
		then:
		matrix.transpose()== resultMatrix
		assert resultMatrix.class == JBlasMatrix
	}
	
	def"sum two matrices"(){
		when:
		Matrix m= new JBlasMatrix(1,3)
		m.setEntry(0,0,1)
		m.setEntry(0,1,2)
		m.setEntry(0,2,3)
		def n= new DoubleMatrix(1,3)
		n=m
		def sum = new DoubleMatrix(1,3)
		m.add(n)== sum
		then:
		def correct = new DoubleMatrix(1,3)
		m.setEntry(0,0,2)
		m.setEntry(0,1,4)
		m.setEntry(0,2,6)
		sum == correct		
	}	
	
	def"substraction of two matrices"(){
		when:
		Matrix m= new JBlasMatrix(1,3)
		m.setEntry(0,0,1)
		m.setEntry(0,1,2)
		m.setEntry(0,2,3)
		def n= new DoubleMatrix(1,3)
		n=m
		Matrix sub1 = new JBlasMatrix(1,3)
		m.subtract(n)== sub1
		Matrix correct = new JBlasMatrix(1,3)
		m.setEntry(0,0,0)
		m.setEntry(0,1,0)
		m.setEntry(0,2,0)
		then:
		sub1 == correct		
	}
	
	def "scalar multiply"(){
		when:
		Matrix m = new JBlasMatrix(1,3)
	    m.setEntry(0,0,1)
		m.setEntry(0,1,2)
		m.setEntry(0,2,3)
		m.scalarMultiply(2)	
		then:
		Matrix m2 = new JBlasMatrix(1,3)
		m2.setEntry(0, 0, 2)
		m2.setEntry(0, 1, 4)
		m2.setEntry(0, 2, 6)
		m == m2
	}
	
	def"dot product"(){
		when:
		def m= new DoubleMatrix(1,3)
		m.put(0,0,1)
		m.put(0,1,2)
		m.put(0,2,3)
		then:
		m.dot(m)== 14
	}
	
	def"get row"(){
		when:
		Matrix m = new JBlasMatrix(1,3)
	    m.setEntry(0,0,1)
		m.setEntry(0,1,2)
		m.setEntry(0,2,3)
		then:		
		m.getRow(0)[0] == 1
		m.getRow(0)[1] == 2
		m.getRow(0)[2] == 3
	}
	
	def"element by element multiply"(){
		when:
		Matrix m = new JBlasMatrix(1,3)
	    m.setEntry(0,0,1)
		m.setEntry(0,1,2)
		m.setEntry(0,2,3)
		Matrix n1 = new JBlasMatrix(1,3)
		n1 =m.ebeMultiply(m)
		then:
		Matrix ntrue= new JBlasMatrix(1,3)
		ntrue.setEntry(0,0,1)
		ntrue.setEntry(0,1,4)
		ntrue.setEntry(0,2,9)
		n1 == ntrue
	}
	
	def "distance between two vectors"(){
		when:
		Matrix m = new JBlasMatrix(3,1)
		m.setEntry(0,0,4)
		m.setEntry(1,0,2)
		m.setEntry(2,0,4)
		Matrix n = new JBlasMatrix(3,1)
		n.setEntry(0,0,0)
		n.setEntry(1,0,0)
		n.setEntry(2,0,0)
		def distance 
		distance = n.distance(m)
		then:
		distance == 6			
	}	
	
	def "calculate norm of a vector"(){
		when:
		Matrix m= new JBlasMatrix(1,3)
		m.setEntry(0,0,4)
		m.setEntry(0,1,1)
		m.setEntry(0,2,0)
		
		then:
		m.getNorm() == 5
	}
}
	
	
