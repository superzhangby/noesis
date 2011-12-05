package noesis;

//Title:       Network ADT
//Version:     1.0
//Copyright:   2011
//Author:      Fernando Berzal
//E-mail:      berzal@acm.org

import ikor.collection.List;
import ikor.collection.graph.*;

/**
 * Network ADT implementation using graphs 
 * 
 * @author Fernando Berzal
 */

public class GraphNetwork<V,E> extends Network<V,E> implements ExplicitGraph<V,E>
{
	private DynamicGraph<V,E> net;
	
	
	public GraphNetwork ()
	{
		this.net = new DynamicGraph<V,E>(true);
	}
	
	
	/* (non-Javadoc)
	 * @see noesis.Network#setSize(int)
	 */
	@Override
	public void setSize (int nodes)
	{
		// Prepare for a given network size
		while (this.size()<nodes)
			this.add(null);
	}
	
	
	// Nodes
	
	/* (non-Javadoc)
	 * @see noesis.Network#add(V)
	 */
	@Override
	public int add (V node)
	{
		if (net.add(node))
			return net.size()-1;
		else
			return -1;
	}

	// Edges

	/* (non-Javadoc)
	 * @see noesis.Network#add(int, int)
	 */
	@Override
	public boolean add (int sourceIndex, int destinationIndex)
	{
		return net.add(sourceIndex, destinationIndex, null);
	}
	
	/* (non-Javadoc)
	 * @see noesis.Network#add(int, int, E)
	 */
	@Override
	public boolean add (int sourceIndex, int destinationIndex, E content)
	{
		return net.add(sourceIndex, destinationIndex, content);
	}
	
	
	
	/* (non-Javadoc)
	 * @see noesis.Network#get(int)
	 */
	@Override
	public V get(int index) 
	{
		return net.get(index);
	}
	
	/* (non-Javadoc)
	 * @see noesis.Network#get(int, int)
	 */
	@Override
	public E get(int source, int destination)
	{
		return net.get(source,destination);
	}

	/* (non-Javadoc)
	 * @see noesis.Network#get(V, V)
	 */
	@Override
	public E get(V source, V destination)
	{
		return net.get(source,destination);
	}
	
	
	/* (non-Javadoc)
	 * @see noesis.Network#size()
	 */
	@Override
	public int size() 
	{
		return net.size();
	}

	/* (non-Javadoc)
	 * @see noesis.Network#contains(V)
	 */
	@Override
	public boolean contains(V object) 
	{
		return net.contains(object);
	}


	/* (non-Javadoc)
	 * @see noesis.Network#isDirected()
	 */
	@Override
	public boolean isDirected() 
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see noesis.Network#index(V)
	 */
	@Override
	public int index(V node) 
	{
		return net.index(node);
	}

	@Override
	public int index(GraphNode<V> node) 
	{
		return net.index(node);
	}

	@Override
	public GraphNode getNode(int index) 
	{
		return net.getNode(index);
	}

	@Override
	public GraphNode<V> getNode(V node) 
	{
		return net.getNode(node);
	}



	/* (non-Javadoc)
	 * @see noesis.Network#inDegree(int)
	 */
	@Override
	public int inDegree(int node) 
	{
		return net.inDegree(node);
	}


	/* (non-Javadoc)
	 * @see noesis.Network#outDegree(int)
	 */
	@Override
	public int outDegree(int node) 
	{
		return net.outDegree(node);
	}


	/* (non-Javadoc)
	 * @see noesis.Network#links()
	 */
	@Override
	public int links() 
	{
		return net.links();
	}


	@Override
	public GraphLink<E> getLink(int source, int destination) 
	{
		return net.getLink(source,destination);
	}


	@Override
	public GraphLink<E> getLink(V source, V destination) 
	{
		return net.getLink(source,destination);
	}


	@Override
	public GraphLink<E> getLink(GraphNode<V> source, GraphNode<V> destination) 
	{
		return net.getLink(source,destination);
	}


	@Override
	public List<GraphLink<E>> outLinkList(int node) 
	{
		return net.outLinkList(node);
	}


	@Override
	public List<GraphLink<E>> outLinkList(V node) 
	{
		return net.outLinkList(node);
	}


	@Override
	public List<GraphLink<E>> inLinkList(int node) 
	{
		return net.inLinkList(node);
	}


	@Override
	public List<GraphLink<E>> inLinkList(V node) 
	{
		return net.inLinkList(node);
	}


	@Override
	public int[] outLinks(int node) 
	{
		return net.outLinks(node);
	}


	@Override
	public int[] outLinks(V node) 
	{
		return net.outLinks(node);
	}


	@Override
	public int[] inLinks(int node) 
	{
		return net.inLinks(node);
	}


	@Override
	public int[] inLinks(V node) 
	{
		return net.inLinks(node);
	}
}