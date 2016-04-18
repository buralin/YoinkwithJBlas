package org.wallerlab.yoink.math.linear

import org.jblas.DoubleMatrix
import org.wallerlab.yoink.api.service.math.Vector;

import spock.lang.Ignore;
import spock.lang.Specification

class JBlasVector3Dtest extends Specification{
	def" create a 3D vector using three double value "(){
		when:
		def jblasvector= new JBlasVector3D(1,1,5)
		def vectorFromLibrary =new DoubleMatrix(3,1)
		vectorFromLibrary.put(0,0,1)
		vectorFromLibrary.put(1,0,1)
		vectorFromLibrary.put(2,0,5)
		then:
		jblasvector.internalVector.equals(vectorFromLibrary)
	}

	def" create a 3D vector using double array "(){
		double[] d= [0.1, 0.1, 0.1]
		when:
		def jblasVector = new JBlasVector3D(d)
		def vectorFromLibrary =new DoubleMatrix(3,1)
		vectorFromLibrary.put(0,0,0.1)
		vectorFromLibrary.put(1,0,0.1)
		vectorFromLibrary.put(2,0,0.1)
		then:
		jblasVector.internalVector.equals(vectorFromLibrary) == true
	}
	def"test dot produt"(){
		when:
		Vector jblasVector= new JBlasVector3D(1,2,2)
		then:
		jblasVector.dotProduct() == 9
	}
	
	def"test vector norm"(){
		when:
		Vector jblasVector= new JBlasVector3D(1,2,2)
		then:
		jblasVector.getNorm() == 3
	}
	
	def "test operator add"(){
		when:
		Vector v1 = new JBlasVector3D(1,1,1)
		Vector v2 = new JBlasVector3D(2,2,2)
		Vector v3 = new JBlasVector3D(3,3,3)
		then:
		v1.add(v2) == v3
	}
	
	def "test operator substract"(){
		when:
		Vector v1 = new JBlasVector3D(1,1,1)
		Vector v2 = new JBlasVector3D(2,2,2)
		Vector v3 = new JBlasVector3D(1,1,1)
		then:
		v2.subtract(v1) == v3
	}

	def "test operator scalarMultiply"(){
		when:
		Vector v1= new JBlasVector3D(1,1,1)
		Vector v2= new JBlasVector3D(2,2,2)
		then:
		v1.scalarMultiply(2) == v2
	}

	def "test element getter methods"(){
		when:
		def v= new JBlasVector3D(1,2,3)
		then:
		v.getX()==1
		v.getY()==2
		v.getZ()==3
	}

	def "test method getEntry(int i)"(){
		when:
		def v= new JBlasVector3D(1,2,3);
		then:
		v.getEntry(0) == 1
		v.getEntry(1) == 2
		v.getEntry(2) == 3
	}
	
	def "test method ebeMultiply()"(){
		when:
		def v1 = new JBlasVector3D(2,2,2)
		def v2 = new JBlasVector3D(2,2,2)
		def v3 = new JBlasVector3D(4,4,4)
		def result = v1.ebeMultiply(v2) 
		then:
		  result == v3
	}
		
}

