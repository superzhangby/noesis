package ikor.math.statistics;

import ikor.math.random.Random;

/**
 * Pareto distribution (a.k.a. power law distribution)
 * http://en.wikipedia.org/wiki/Pareto_distribution
 * 
 * @author Fernando Berzal (berzal@acm.org)
 */
public class ParetoDistribution implements Distribution 
{
	private double lambda; // a.k.a. scale parameter, sigma, or x_min
	private double kappa;  // a.k.a. shape parameter, tail index, or alpha

	/**
	 * Pareto distribution
	 * @param exponent > 0
	 */
	public ParetoDistribution (double shape)
	{
		this(shape,1);
	}
	
	/**
	 * Pareto distribution
	 * @param exponent > 0
	 * @param scale > 0
	 */

	public ParetoDistribution (double shape, double scale)
	{
		this.kappa = shape;
		this.lambda = scale;
	}
	

	@Override
	public double pdf(double x) 
	{
		if (x>lambda)
			return kappa*Math.pow(lambda,kappa)/Math.pow(x,kappa+1);
		else
			return 0.0;
	}

	@Override
	public double cdf(double x) 
	{
		if (x>lambda)
			return 1.0 - Math.pow(lambda/x, kappa);
		else
			return 0.0;
	}
	

	@Override
	public double idf(double p) 
	{ 
		if (p==0)
			return lambda;
		else if (p==1)
			return Double.POSITIVE_INFINITY;
		else
			return lambda * Math.pow(1-p, -1/kappa);
	}
	

	// Random number generator

	@Override
	public double random() 
	{
		return lambda * Math.pow( Random.random(), -1/kappa );
	}

	
	// Statistics
	
	@Override
	public double mean() 
	{
		if (kappa<=1)
			return Double.POSITIVE_INFINITY;
		else
			return kappa*lambda/(kappa-1);
	}

	@Override
	public double variance() 
	{
		if (kappa<=2)
			return Double.POSITIVE_INFINITY;
		else
			return (kappa*lambda*lambda)/((kappa-1)*(kappa-1)*(kappa-2));
	}

	@Override
	public double skewness() 
	{
		if (kappa<=3)
			return Double.NaN;
		else
			return ( 2*(1+kappa)/(kappa-3) ) * Math.sqrt ( (kappa-2)/kappa );
	}

	@Override
	public double kurtosis() 
	{
		if (kappa<=4)
			return Double.NaN;
		else
			return 6 * ( kappa*kappa*kappa + kappa*kappa - 6*kappa - 2 ) / ( kappa*(kappa-3)*(kappa-4) );
	}

}