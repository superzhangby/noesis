package test.noesis.algorithms.paths;

import static org.junit.Assert.*;

import test.noesis.SampleNetworks;
import test.noesis.algorithms.DirectLinkEvaluator;

import noesis.Network;
import noesis.LinkEvaluator;
import noesis.algorithms.paths.*;


public abstract class AllPairsShortestPathFinderTest
{
	protected final double EPSILON = 0.000001;
	protected Network<String,Integer> network;
	protected AllPairsShortestPathFinder finder;	
	
	// Abstract factory method
	
	public abstract AllPairsShortestPathFinder pathFinder (Network net, LinkEvaluator linkEvaluator);

	
	// Check distance
	
	public void checkDistance (String source, String destination, double expected)
	{
		assertEquals( expected, finder.distance(network.index(source),network.index(destination)), EPSILON );
	}
	
	public void checkDistance (int source, int destination, double expected)
	{
		String message = "Expected distance from "+network.get(source)+" to "+network.get(destination)+" = "+expected;
		
		assertEquals( "ERROR: "+message, expected, finder.distance(source,destination), EPSILON);
	}
	
	// Common tests

	public void checkConnected () 
	{
		network = SampleNetworks.weightedDirectedGraph();
		
		LinkEvaluator linkEvaluator = new DirectLinkEvaluator(network);
		
		finder = pathFinder(network,linkEvaluator);
			
		finder.run();
		
		for (int i=0; i<network.size(); i++)
			for (int j=0; j<network.size(); j++)
				checkDistance(i,j, SampleNetworks.DIRECTED_DISTANCE[i][j]);
	}	

	
	public void checkUnreachable() 
	{
		network = SampleNetworks.weightedUnreachableGraph();
		
		LinkEvaluator linkEvaluator = new DirectLinkEvaluator(network);

		finder = pathFinder(network,linkEvaluator);
		
		finder.run();
		
		for (int i=0; i<network.size(); i++)
			for (int j=0; j<network.size(); j++)
				checkDistance(i,j, SampleNetworks.UNREACHABLE_DISTANCE[i][j]);
	}	
	
	
	public void checkDisconnected() 
	{
		network = SampleNetworks.weightedDisconnectedGraph();;

		LinkEvaluator linkEvaluator = new DirectLinkEvaluator(network);
				
		finder = pathFinder(network,linkEvaluator);
		
		finder.run();
		
		for (int i=0; i<network.size(); i++)
			for (int j=0; j<network.size(); j++)
				checkDistance(i,j, SampleNetworks.DISCONNECTED_DISTANCE[i][j]);
	}	

}
