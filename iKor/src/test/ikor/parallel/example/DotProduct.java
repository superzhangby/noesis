package test.ikor.parallel.example;

import ikor.parallel.Parallel;

import ikor.parallel.Kernel;

import ikor.parallel.Scheduler;
import ikor.parallel.scheduler.*;

import ikor.parallel.combiner.AddCombiner;

import ikor.util.Benchmark;


public class DotProduct implements Kernel<Double>
{
	private static final int SIZE = 16777216; // 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432 OK (ms)
	                                          // 67108864 -> OutOfMemoryError: Java heap space

	private double x[];
	private double y[];
		
	public DotProduct (double x[], double y[])
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Double call(int index) 
	{
		return x[index] * y[index];
	}
	
	public double product ()
	{
		return (Double) Parallel.reduce(this, new AddCombiner(), 0, SIZE-1);
	}

	
	// Test program

	public static void main (String[] args) 
	{
		
		Scheduler.set ( new WorkStealingScheduler(8) ); // 4 (i5) vs. 8 (i7)
		//Scheduler.set ( new ThreadPoolScheduler() );	
		//Scheduler.set ( new FutureScheduler(16) );	
		//Scheduler.set ( new SequentialScheduler() );
		
		Parallel.setDecompositionDepth(4);
		Parallel.setTileWidth(16); 
		
		
		// Input data
		
		double x[] = new double[SIZE];
		double y[] = new double[SIZE];
		double expected = 0;
		
		for (int i=0; i<SIZE; i++) {
			x[i] = Math.random();
			y[i] = Math.random();
			expected += x[i]*y[i];
		}
		
		
		// Computation

		Benchmark chrono = new Benchmark();

		chrono.start();
		
		DotProduct dot = new DotProduct(x,y);
		
		double result = dot.product();
		
		chrono.stop();


		// Result

		System.out.println("Result:   "+result);
		System.out.println("Expected: "+expected);
		
		if (Math.abs((result-expected)/expected)>1e-9) // NOTE: Floating point operations are not fully-associative.
			System.err.println("Error in dot product computation.");
		else
			System.out.println("OK");

		System.out.println("Time: "+chrono);
		
		Scheduler.get().shutdown();
	}

}             