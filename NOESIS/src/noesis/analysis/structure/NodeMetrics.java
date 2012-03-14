package noesis.analysis.structure;


import noesis.Network;

import ikor.math.Vector;

public abstract class NodeMetrics 
{
	private Network network;
	private Vector  metrics;
	
	protected NodeMetrics (Network network)
	{
		this.network = network;
		this.metrics = new Vector(network.size());
	}
	
	public final Network getNetwork ()
	{
		return this.network;
	}
	
	public final Vector getVector ()
	{
		return this.metrics;
	}
	
	public final double get(int i)
	{
		return metrics.get(i);
	}
	
	public final void set (int i, double value)
	{
		metrics.set(i,value);
	}
	
	
	// Computation
	
	protected boolean done = false;
	
	public void compute ()
	{
		Network net = getNetwork();
		int     size = net.size();
	
		for (int node=0; node<size; node++)
			set (node, compute(node));
		
		done = true;
	}
	
	public final boolean checkDone ()
	{
		if (!done)
			compute();
		
		return done;
	}
	
	public abstract double compute (int node);
	
	// Standard output
	
	public String toString ()
	{
		return metrics.toStringSummary();
	}
}
