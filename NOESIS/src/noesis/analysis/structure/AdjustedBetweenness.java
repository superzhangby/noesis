package noesis.analysis.structure;

import ikor.model.data.annotations.Description;
import ikor.model.data.annotations.Label;
import noesis.Network;
import noesis.algorithms.traversal.StronglyConnectedComponents;
import noesis.analysis.NodeScore;

// Betweenness centrality adjusted to component sizes
// - Freeman's betweenness between (2n-1) and (n^2-(n-1)) in strongly-connected networks
// - Adjustment:  ( score - (2n-1) ) where n is the size of the strongly-connected component

@Label("adj-betweenness")
@Description("Adjusted betweenness")
public class AdjustedBetweenness extends Betweenness
{
	public AdjustedBetweenness (Network network)
	{
		super(network);
	}	

	@Override
	public String getName() 
	{
		return "betweenness";
	}	

	@Override
	public String getDescription() 
	{
		return "Betweenness";
	}		

	@Override
	public void compute ()
	{
		super.compute();

		NodeScore score = getResult();
		
		// Adjust taking into account component sizes
		
		StronglyConnectedComponents scc;
		
		scc = new StronglyConnectedComponents( getNetwork() );
		scc.compute();

		for (int node=0; node<getNetwork().size(); node++) {
			int size = scc.componentSize(node);
			score.set ( node, score.get(node) - (2*size-1) );
		}
		
		setResult(score);
	}
}
