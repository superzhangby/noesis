package sandbox.math;

import ikor.math.Functions;

/**
 * Beta distribution
 * http://en.wikipedia.org/wiki/Beta_distribution
 * 
 * @author Fernando Berzal (berzal@acm.org)
 */
public class BetaDistribution implements Distribution 
{
	private double alpha;
	private double beta;
	private double factor;
	
	/**
	 * Beta distribution
	 * @param alpha shape parameter
	 * @param beta shape parameter
	 */
	public BetaDistribution (double alpha, double beta)
	{
		this.alpha  = alpha;
		this.beta   = beta;
		this.factor = -Functions.logBeta(alpha,beta);
	}

	@Override
	public double pdf(double x) 
	{
		if ( (x<=0) || (x>=1) )
			return 0;
		else
			return Math.exp((alpha-1)*Math.log(x)+(beta-1)*Math.log(1-x)+factor);
	}

	@Override
	public double cdf(double x) 
	{
		if (x<=0)
			return 0.0;
		else if (x>=1)
			return 1.0;
		else
			return Functions.betaI(alpha, beta, x);
	}
	

	@Override
	public double idf(double p) 
	{ 
		if (p==0)
			return 0.0;
		else if (p==1)
			return 1.0;
		else
			return Functions.betaIinv(p, alpha, beta);
	}
	


	@Override
	public double random() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double mean() 
	{
		return alpha/(alpha+beta);
	}

	@Override
	public double variance() 
	{
		return alpha*beta / ( (alpha+beta)*(alpha+beta)*(alpha+beta+1) );
	}

	@Override
	public double skewness() 
	{
		return 2*(beta-alpha)*Math.sqrt(alpha+beta+1) / ( (alpha+beta+2)*Math.sqrt(alpha*beta) );
	}

	@Override
	public double kurtosis() 
	{
		return 6 * ( (alpha-beta)*(alpha-beta)*(alpha+beta+1) - alpha*beta*(alpha+beta+2) )
				   / ( alpha*beta*(alpha+beta+2)*(alpha+beta+3) );
	}

}
