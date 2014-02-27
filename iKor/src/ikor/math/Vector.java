package ikor.math;

public class Vector extends Matrix 
{
	private double  data[];
	private boolean transposed;
	
	// Constructors
	
	public Vector (int size)
	{
		this.data = new double[size];
		this.transposed = false;
	}
	
	public Vector (Vector original)
	{
		this(original.size());
		
		for (int i=0; i<data.length; i++)
			this.data[i] = original.data[i];
		
		this.transposed = original.transposed;
	}

	public Vector (double[] data)
	{
		this.data = data;
		this.transposed = false;
	}
	
	public Vector (Matrix data, int row)
	{
		this(data.columns());
		
		for (int j=0; j<data.columns(); j++)
			this.data[j] = data.get(row, j);
	}
	
	// Dimensions


	public int rows() 
	{
		if (transposed)
			return data.length;
		else
			return 1;
	}

	public int columns() 
	{
		if (transposed)
			return 1;
		else
			return data.length;
	}
	
	
	// Accessors & mutators
	
	public double get (int i)
	{
		return data[i];
	}
	
	public void set (int i, double value)
	{
		data[i] = value;
	}
	
	public void set (double[] values)
	{
		data = values;
	}

	// Matrix interface

	public double get(int i, int j) 
	{
		return data[Math.max(i,j)];
	}

	public void set(int i, int j, double v) 
	{
		data[Math.max(i,j)] = v;
	}
	
	public void set(int i, double v[])
	{
		data = v;
	}
	
	public void set(double v[][])
	{
		if (v.length==1) {
			data = v[0];
		} else {
			data = new double[v.length];
			
			for (int i=0; i<v.length; i++)
				data[i] = v[i][0];
		}
		
	}
	
	public void swap (int i, int j)
	{
		double tmp;
		
		tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	
	// Transposed vector

	public Vector transpose() 
	{
		Vector result = new Vector(this.data);
		
		result.transposed = !this.transposed;

		return result;
	}

	
	// Arithmetic
	
	/**
	 * In-place vector addition 
	 * @param other Vector to be added to the current vector
	 * @return Updated vector
	 * @pre (this.size() == other.size())
	 */
	
	public Vector accumulate (Vector other) 
	{
		int dim = this.size();

		for (int i=0; i<dim; i++)
			data[i] += other.data[i];

		return this;
	}
	
	
	public double magnitude ()
	{
		return Math.sqrt(magnitude2());
	}
	
	public double magnitude2 ()
	{
		double result = 0;
		
		for (int i=0; i<size(); i++)
			result += get(i)*get(i);
		
		return result;
	}
	
	
	public double dotProduct (Vector other)
	{
		double result = 0;
		
		for (int i=0; i<size(); i++)
			result += this.get(i)*other.get(i);
		
		return result;
	}
	
	
	public double projection (Vector other)
	{
		double length = other.magnitude();
		
		if (length>0)
			return dotProduct(other)/length;
		else
			return 0;
	}
	
	
	public double distance (Vector other)
	{
		return Math.sqrt(distance2(other));
	}
		

	public double distance2 (Vector other)
	{
		double diff;
		double result = 0;
		
		for (int i=0; i<size(); i++) {
			diff = this.get(i) - other.get(i);
			result += diff*diff;
		}
		
		return result;		
	}	
	
	
	public double angle (Vector other)
	{
		double a = this.magnitude();
		double b = other.magnitude();
		double p = this.dotProduct(other);
		
		if ( (a>0) && (b>0) ) {
			
			if ( Math.abs(p-a*b) < Configuration.EPSILON )
				return 0;
			else
				return Math.acos( p / (a*b) );
			
		} else
			return 0;
	}
	
	
	// Basic statistics
	
	public double min ()
	{
		double min = get(0);
		
		for (int i=1; i<size(); i++)
			if (get(i)<min)
				min = get(i);
		
		return min;
	}

	public int minIndex ()
	{
		int min = 0;
		
		for (int i=1; i<size(); i++)
			if (get(i)<get(min))
				min = i;
		
		return min;
	}

	public double max ()
	{
		double max = get(0);
		
		for (int i=1; i<size(); i++)
			if (get(i)>max)
				max = get(i);
		
		return max;
	}

	public int maxIndex ()
	{
		int max = 0;
		
		for (int i=1; i<size(); i++)
			if (get(i)>get(max))
				max = i;
		
		return max;
	}

	public double sum ()
	{
		double sum = 0;
		
		for (int i=0; i<size(); i++)
			sum += get(i);
		
		return sum;
	}	

	public double sum2 ()
	{
		double sum2 = 0;
		
		for (int i=0; i<size(); i++)
			sum2 += get(i)*get(i);
		
		return sum2;
	}	
	
	public double average ()
	{
		return sum()/size();
	}	

	public double variance ()
	{
		double avg = average();
		
		return sum2()/size() - avg*avg;
	}	
	
	public double deviation ()
	{
		return Math.sqrt(variance());
	}	

	public double absoluteDeviation ()
	{
		double avg = average();
		double dev = 0;
		
		for (int i=0; i<size(); i++)
			dev += Math.abs(get(i)-avg);
		
		return dev/size();
	}	
	
	
	// Overriden Object methods
	
	@Override
	public boolean equals (Object obj)
	{
		if (this==obj) {
		
			return true;
			
		} else if (obj instanceof Vector) {
				
				Vector other = (Vector) obj;
				
				for (int i=0; i<size(); i++)
					if ( this.get(i) != other.get(i) )
						return false;
				
				return true;

		} else {
			
			return false;
		}
	}
	
	@Override 
	public int hashCode ()
	{
		return this.toString().hashCode(); 
	}	
	
	// Standard output
	
	@Override
	public String toString() 
	{
		return toString("[","]",",");
	}

	public String toStringSummary() 
	{
		if (size()>0)
			return "[n=" + size()
					+ " min=" + min()
					+ " max=" + max()
					+ " avg=" + average()
					+ " dev=" + deviation() + "]";
		else
			return "[n=0]";
	}
	
	
	public String toString(String prefix, String suffix, String delimiter) 
	{
		int i, n;
		StringBuffer buffer = new StringBuffer();
		
		n = size();
		
		buffer.append(prefix);
		buffer.append(get(0));
		
		for (i=1; i<n; i++) {
			buffer.append(delimiter);
			buffer.append(get(i));
		}
		
		buffer.append(suffix);
		
		return buffer.toString();
	}

}
