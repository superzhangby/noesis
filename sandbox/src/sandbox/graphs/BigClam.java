package sandbox.graphs;

import ikor.math.DenseMatrix;
import ikor.math.DenseVector;
import ikor.math.Matrix;
import ikor.math.Vector;
import ikor.math.random.Random;
import noesis.BasicNetwork;
import noesis.Network;

public class BigClam 
{
	public static final double DEFAULT_LEARNING_RATE = 0.05;
	public static final int DEFAULT_MAX_ITERATIONS = 1000;
	public static final double DEFAULT_EPSILON = 0.01;
	
	Network network;
	int k;
	
	Matrix strength;
	double learningRate;
	int maxIterations;
	double epsilon;
	double J[];
	
	public BigClam (Network network, int k)
	{
		this.network = network;
		this.k = k;
	
		maxIterations = DEFAULT_MAX_ITERATIONS;
		learningRate = DEFAULT_LEARNING_RATE;
		epsilon = DEFAULT_EPSILON;
		
		// Initialization

		strength = new DenseMatrix(network.size(),k);
		
		for (int i=0; i<strength.rows(); i++)
			for (int j=0; j<strength.columns(); j++)
				strength.set(i,j, Random.random() );
		
		// Coordinate gradient ascent
		
		for (int i=0; (i<maxIterations) && !hasConverged(i); i++) {
			strength = updateParameters(strength);
			System.err.println(i+ " "+maxDelta);
		}
			
	}
	
	double maxDelta;
	
	public Matrix updateParameters (Matrix F)
	{
		Vector sum = columnSum(F);
		Vector newFrow = new DenseVector(F.columns());
		
		maxDelta = 0;
		
		for (int row=0; row<F.rows(); row++) {
			
			// Compute gradient
			
			Vector gradient = gradient(F,row,sum);
		
			// Update row: F_u = F_u + nu * grad(F_u)

			for (int column=0; column<F.columns(); column++) {
				double oldF = F.get(row,column);
				double newF = oldF + learningRate*gradient.get(column);
				
				if (newF<0)		 // Project to non-negative vector
					newF = 0.0;

				newFrow.set(column,newF);
				
				if (Math.abs(newF-oldF)>maxDelta)
					maxDelta = Math.abs(newF-oldF);
			}
			
			sum = sum.add(newFrow).subtract(F.getRow(row));
			F.setRow(row, newFrow);
		}
		
		return F;
	}	
	
	
	public Vector gradient (Matrix F, int node, Vector sum)
	{
		Vector grad = new DenseVector(F.columns());
		
		for (int link=0; link<network.outDegree(node); link++) {
			int neighbor = network.outLink(node, link);
			double np = Math.exp( - F.getRow(node).dotProduct( F.getRow(neighbor) ) );
			
			grad = grad.add( F.getRow(neighbor).multiply( np / (1-np) ) );
		}
		
		// - sum(F_v) for non-neighbors
		
		grad = grad.subtract( sum );
		grad = grad.add( F.getRow(node) );

		for (int link=0; link<network.outDegree(node); link++) {
			int neighbor = network.outLink(node, link);
			grad = grad.add ( F.getRow(neighbor) );
		}
		
		return grad;
	}
	
	public Vector columnSum (Matrix matrix)
	{
		Vector sum = new DenseVector(matrix.columns());
		
		for (int i=0; i<matrix.columns(); i++)
			sum.set(i, matrix.getColumn(i).sum() );
		
		return sum;
	}
	
	// Check for convergence
	
	public boolean hasConverged (int iteration)
	{
		// TODO delta(l(F))
		return (iteration>1) && (maxDelta<epsilon);
	}
	
	public String toString ()
	{
		String str = strength.rows()+ " nodes, "+ strength.columns()+" communities:\n";
		
		str += strength.toString();
		
		return str;
	}

	// Test network
	
	private final static int K1 = 5;
	private final static int K2 = 5;
	
	public static Network createNetwork ()
	{
		Network network = new BasicNetwork();
		
		network.setSize(K1+K2);
		
		for (int i=0; i<K1; i++) {
			for (int j=i+1; j<K1; j++) {
				network.add2(i, j);
			}
		}

		for (int i=0; i<K2; i++) {
			for (int j=i+1; j<K2; j++) {
				network.add2(K1+i, K1+j);
			}
		}
		
		// network.add2(K1-1, K1);
		// network.add2(K1-1, K1+1);
		
		return network;
	}
	
	
	public static void main (String args[])
	{
		Network network = createNetwork();
		
		BigClam communities = new BigClam(network,2);
		
		System.out.println(communities);
	}

}
