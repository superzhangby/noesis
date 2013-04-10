package noesis;

/**
 * Network link attribute
 * 
 * @author Fernando Berzal
 *
 * @param <T> Base type
 */

public class LinkAttribute<T> extends Attribute<T> 
{
	AttributeNetwork net;
	
	public LinkAttribute (AttributeNetwork net, String id)
	{
		super(id);
		this.net = net;
	}
	
	protected int index (int source, int target)
	{
		return net.index(source,target);
	}
	
	public T get (int source, int target)
	{
		int index = index(source,target);
		
		if (index!=-1)
			return get(index);
		else
			return null;
	}
	
	public T set (int source, int target, T value)
	{
		int index = index(source,target);
		
		if (index!=-1)
			return set(index,value);
		else
			return null;
	}
	
}