package ikor.parallel.combiner;

import ikor.math.MatrixFactory;
import ikor.math.Vector;
import ikor.parallel.Combiner;

public class VectorAccumulator implements Combiner<Vector> 
{
	private int size;
	
	public VectorAccumulator (int size)
	{
		this.size = size;
	}
	
	@Override
	public Vector identity() 
	{
		return MatrixFactory.createVector(size);
	}

	@Override
	public Vector combine (Vector first, Vector second) 
	{
		return first.accumulate(second);
	}
}
