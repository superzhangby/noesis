package noesis.analysis.structure;

import ikor.model.data.annotations.Description;
import ikor.model.data.annotations.Label;
import noesis.Network;

@Label("cc")
@Description("Clustering coefficient")
public class ClusteringCoefficient extends NodeMeasureTask
{
	public ClusteringCoefficient (Network network)
	{
		super(network);
	}
	
	public double compute (int node) 
	{
		Network net = getNetwork();
		int triangles = 0;
		int degree  = net.outDegree(node);
		int links[] = net.outLinks(node);
		
		if (links!=null) {			
			for (int i=0; i<degree; i++) {
				for (int j=0; j<degree; j++) {				
				
					if (net.contains(links[i],links[j]))
						triangles++;
				}
			}
		}
		
		if (degree>1)
			return ((double)triangles)/(degree*(degree-1));
		else
			return 0;
	}

}
