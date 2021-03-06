package test.ikor.math.statistics;

import static org.junit.Assert.*;
import ikor.math.statistics.NormalDistribution;
import ikor.math.statistics.StudentTDistribution;

import org.junit.Before;
import org.junit.Test;

public class StudentTDistributionTest 
{
	public static double ERROR = 1e-7;
	
	@Before
	public void setUp() throws Exception 
	{
	}

	// Standard (0,1)-distribution with 1 degree of freedom == Cauchy distribution
	
	@Test
	public void testStandardPDF() 
	{
		StudentTDistribution standard = new StudentTDistribution(1,0,1);
		
		assertEquals( 0.0, standard.pdf(-1e10), ERROR );
		assertEquals( 1/Math.PI, standard.pdf( 0.0), ERROR );
		assertEquals( 0.0, standard.pdf(+1e10), ERROR );
	}
	
	@Test
	public void testStandardCDF() 
	{
		StudentTDistribution standard = new StudentTDistribution(1,0,1);
		
		assertEquals( 0.0, standard.cdf(-1e16), ERROR );
		assertEquals( 0.5, standard.cdf( 0.0), ERROR );
		assertEquals( 1.0, standard.cdf(+1e16), ERROR );
	}
	
	@Test
	public void testStandardIDF() 
	{
		StudentTDistribution standard = new StudentTDistribution(1,0,1);
		
		assertEquals( Double.NEGATIVE_INFINITY, standard.idf(0.0), ERROR );
		assertEquals(  0.0, standard.idf(0.5), ERROR );
		assertEquals( Double.POSITIVE_INFINITY, standard.idf(1.0), ERROR );
	}	

	@Test
	public void testStandardStatistics() 
	{
		StudentTDistribution standard = new StudentTDistribution(1,0,1);
		
		assertEquals( Double.NaN, standard.mean(), ERROR );
		assertEquals( Double.NaN, standard.variance(), ERROR );
		assertEquals( Double.NaN, standard.skewness(), ERROR );
		assertEquals( Double.NaN, standard.kurtosis(), ERROR );
	}
	
	// C(1,1)

	@Test
	public void test11CauchyPDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,1,1);
		
		assertEquals( 0.0, cauchy.pdf(-1e16), ERROR );
		assertEquals( 1/Math.PI, cauchy.pdf(1.0), ERROR );
		assertEquals( 0.0, cauchy.pdf(+1e16), ERROR );
	}
	
	@Test
	public void test11CauchyCDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,1,1);
		
		assertEquals( 0.0, cauchy.cdf(-1e16), ERROR );
		assertEquals( 0.5, cauchy.cdf( 1.0), ERROR );
		assertEquals( 1.0, cauchy.cdf(+1e16), ERROR );
	}
	
	@Test
	public void test11CauchyIDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,1,1);
		
		assertEquals( Double.NEGATIVE_INFINITY, cauchy.idf(0.0), ERROR );
		assertEquals( 1.0, cauchy.idf(0.5), ERROR );
		assertEquals( Double.POSITIVE_INFINITY, cauchy.idf(1.0), ERROR );
	}

	@Test
	public void test11CauchyStatistics() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,1,1);
		
		assertEquals( Double.NaN, cauchy.mean(), ERROR );
		assertEquals( Double.NaN, cauchy.variance(), ERROR );
		assertEquals( Double.NaN, cauchy.skewness(), ERROR );
		assertEquals( Double.NaN, cauchy.kurtosis(), ERROR );
	}
	
	// C(0,2)

	@Test
	public void test02CauchyPDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,2);
		
		assertEquals( 0.0, cauchy.pdf(-1e16), ERROR );
		assertEquals( 1/(2*Math.PI), cauchy.pdf(0.0), ERROR );
		assertEquals( 0.0, cauchy.pdf(+1e16), ERROR );
	}
	
	@Test
	public void test02CauchyCDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,2);
		
		assertEquals( 0.0, cauchy.cdf(-1e16), ERROR );
		assertEquals( 0.5, cauchy.cdf(  0.0), ERROR );
		assertEquals( 1.0, cauchy.cdf(+1e16), ERROR );
	}
	
	@Test
	public void test02CauchyIDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,2);
		
		assertEquals( Double.NEGATIVE_INFINITY, cauchy.idf(0.0), ERROR );
		assertEquals( 0.0, cauchy.idf(0.5), ERROR );
		assertEquals( Double.POSITIVE_INFINITY, cauchy.idf(1.0), ERROR );
	}

	@Test
	public void test02CauchyStatistics() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,2);
		
		assertEquals( Double.NaN, cauchy.mean(), ERROR );
		assertEquals( Double.NaN, cauchy.variance(), ERROR );
		assertEquals( Double.NaN, cauchy.skewness(), ERROR );
		assertEquals( Double.NaN, cauchy.kurtosis(), ERROR );
	}
	
	// C(5,2)
	
	@Test
	public void test52CauchyPDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,5,2);
		
		assertEquals( 0.0, cauchy.pdf(-1e16), ERROR );
		assertEquals( 1/(2*Math.PI), cauchy.pdf(5.0), ERROR );
		assertEquals( 0.0, cauchy.pdf(+1e16), ERROR );
	}
	
	
	@Test
	public void test52CauchyCDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,5,2);
				
		assertTrue( -1e16 > cauchy.idf(0.0) );
		assertEquals( 0.5, cauchy.cdf(  5.0), ERROR );
		assertTrue( +1e16 < cauchy.idf(1.0) );
	}
	
	@Test
	public void test52CauchyIDF() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,5,2);
		
		assertEquals( Double.NEGATIVE_INFINITY, cauchy.idf(0.0), ERROR );
		assertEquals( 5.0, cauchy.idf(0.5), ERROR );
		assertEquals( Double.POSITIVE_INFINITY, cauchy.idf(1.0), ERROR );
	}
	
	@Test
	public void test52CauchyStatistics() 
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,5,2);
		
		assertEquals( Double.NaN, cauchy.mean(), ERROR );
		assertEquals( Double.NaN, cauchy.variance(), ERROR );
		assertEquals( Double.NaN, cauchy.skewness(), ERROR );
		assertEquals( Double.NaN, cauchy.kurtosis(), ERROR );
	}
	
	// Degrees of freedom
	
	// Tolerance intervals
	
	@Test
	public void testToleranceIntervals ()
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,1);
		
		assertEquals( 1.0/3.0, cauchy.cdf(Math.sqrt(3)/3) - cauchy.cdf(-Math.sqrt(3)/3), ERROR); 
		assertEquals( 0.5,     cauchy.cdf(1) - cauchy.cdf(-1), ERROR);
		assertEquals( 2.0/3.0, cauchy.cdf(Math.sqrt(3)) - cauchy.cdf(-Math.sqrt(3)), ERROR); 
		assertEquals( 1.0,     cauchy.cdf(1e10) - cauchy.cdf(-1e10), ERROR);
	}
	
	// degrees of freedom -> infinity  ==>  t disitribution == normal distribution

	public static double NORMAL_ERROR = 1e-5;
	
	@Test
	public void testNormal ()
	{
		StudentTDistribution t = new StudentTDistribution(1000000000,0,1);
		NormalDistribution   normal = new NormalDistribution(0,1);
		
		assertEquals ( normal.pdf(0), t.pdf(0), NORMAL_ERROR );
		assertEquals ( normal.pdf(1), t.pdf(1), NORMAL_ERROR );
		assertEquals ( normal.pdf(2), t.pdf(2), NORMAL_ERROR );
		assertEquals ( normal.pdf(3), t.pdf(3), NORMAL_ERROR );
		assertEquals ( normal.pdf(4), t.pdf(4), NORMAL_ERROR );
		assertEquals ( normal.pdf(5), t.pdf(5), NORMAL_ERROR );
		assertEquals ( normal.pdf(6), t.pdf(6), NORMAL_ERROR );
		assertEquals ( normal.pdf(-1), t.pdf(-1), NORMAL_ERROR );
		assertEquals ( normal.pdf(-2), t.pdf(-2), NORMAL_ERROR );
		assertEquals ( normal.pdf(-3), t.pdf(-3), NORMAL_ERROR );
		assertEquals ( normal.pdf(-4), t.pdf(-4), NORMAL_ERROR );
		assertEquals ( normal.pdf(-5), t.pdf(-5), NORMAL_ERROR );
		assertEquals ( normal.pdf(-6), t.pdf(-6), NORMAL_ERROR );
		
		assertEquals ( normal.cdf(0), t.cdf(0), NORMAL_ERROR );
		assertEquals ( normal.cdf(1), t.cdf(1), NORMAL_ERROR );
		assertEquals ( normal.cdf(2), t.cdf(2), NORMAL_ERROR );
		assertEquals ( normal.cdf(3), t.cdf(3), NORMAL_ERROR );
		assertEquals ( normal.cdf(4), t.cdf(4), NORMAL_ERROR );
		assertEquals ( normal.cdf(5), t.cdf(5), NORMAL_ERROR );
		assertEquals ( normal.cdf(6), t.cdf(6), NORMAL_ERROR );
		assertEquals ( normal.cdf(-1), t.cdf(-1), NORMAL_ERROR );
		assertEquals ( normal.cdf(-2), t.cdf(-2), NORMAL_ERROR );
		assertEquals ( normal.cdf(-3), t.cdf(-3), NORMAL_ERROR );
		assertEquals ( normal.cdf(-4), t.cdf(-4), NORMAL_ERROR );
		assertEquals ( normal.cdf(-5), t.cdf(-5), NORMAL_ERROR );
		assertEquals ( normal.cdf(-6), t.cdf(-6), NORMAL_ERROR );

		assertEquals ( normal.idf(0.0), t.idf(0.0), NORMAL_ERROR );
		assertEquals ( normal.idf(0.1), t.idf(0.1), NORMAL_ERROR );
		assertEquals ( normal.idf(0.2), t.idf(0.2), NORMAL_ERROR );
		assertEquals ( normal.idf(0.3), t.idf(0.3), NORMAL_ERROR );
		assertEquals ( normal.idf(0.4), t.idf(0.4), NORMAL_ERROR );
		assertEquals ( normal.idf(0.5), t.idf(0.5), NORMAL_ERROR );
		assertEquals ( normal.idf(0.6), t.idf(0.6), NORMAL_ERROR );
		assertEquals ( normal.idf(0.7), t.idf(0.7), NORMAL_ERROR );
		assertEquals ( normal.idf(0.8), t.idf(0.8), NORMAL_ERROR );
		assertEquals ( normal.idf(0.9), t.idf(0.9), NORMAL_ERROR );
		assertEquals ( normal.idf(1.0), t.idf(1.0), NORMAL_ERROR );
	}
	
	// Quantile function: z_p
	
	@Test
	public void testQuantiles ()
	{
		StudentTDistribution cauchy = new StudentTDistribution(1,0,1);

		assertEquals( Math.tan((0.8-0.5)*Math.PI), cauchy.idf(0.80), ERROR);
		assertEquals( Math.tan((0.9-0.5)*Math.PI), cauchy.idf(0.90), ERROR);

		assertEquals( Math.tan((0.95-0.5)*Math.PI), cauchy.idf(0.95), ERROR);
		assertEquals( Math.tan((0.98-0.5)*Math.PI), cauchy.idf(0.98), ERROR);
		assertEquals( Math.tan((0.99-0.5)*Math.PI), cauchy.idf(0.99), ERROR);
	}

	
	// t tables (data from Wikipedia, http://en.wikipedia.org/wiki/Student%27s_t_distribution#Table_of_selected_values)
	
	private static final double TERROR = 0.001;
	
	private static final double oneSided[] = { 0.75, 0.90, 0.95, 0.975, 0.99, 0.995, 0.999, 0.9995 };
	private static final double twoSided[] = { 0.50, 0.80, 0.90, 0.95,  0.98, 0.99,  0.998, 0.999  };

	private static final double degrees[] =     {     1,     5,    10,   100 }; // Degrees of freedom vs. one-tailed/two-tailed
	private static final double tvalues[][] = { { 1.000, 0.727, 0.700, 0.677 }, // 75% / 50%
		                                        { 3.078, 1.476, 1.372, 1.290 }, // 90% / 80%
		                                        { 6.314, 2.015, 1.812, 1.660 }, // 95% / 90%
		                                        { 12.71, 2.571, 2.228, 1.984 }, // 97.5% / 95%
		                                        { 31.82, 3.365, 2.764, 2.364 }, // 99% / 98%
		                                        { 63.66, 4.032, 3.169, 2.626 }, // 99.5% / 99%
		                                        { 318.3, 5.893, 4.144, 3.174 }, // 99.9% / 99.8%
		                                        { 636.6, 6.869, 4.587, 3.390 }  // 99.95% / 99.9%
	};
	
	@Test
	public void testOneSided ()
	{
		StudentTDistribution t[] = new StudentTDistribution[degrees.length];
		
		for (int j=0; j<t.length; j++)
			t[j] =  new StudentTDistribution(degrees[j],0,1);
		
		for (int i=0; i<tvalues.length; i++) {
			for (int j=0; j<t.length; j++) {
			    assertEquals ( "CDF error @ ("+oneSided[i]+","+tvalues[i][j]+") with "+degrees[j]+" degrees of freedom",
			    		       oneSided[i], t[j].cdf(tvalues[i][j]), oneSided[i]*TERROR );	
			}
		}
	}

	@Test
	public void testOneSidedInverse ()
	{
		StudentTDistribution t[] = new StudentTDistribution[degrees.length];
		
		for (int j=0; j<t.length; j++)
			t[j] =  new StudentTDistribution(degrees[j],0,1);
		
		for (int i=0; i<tvalues.length; i++) {
			for (int j=0; j<t.length; j++) {
			    assertEquals ( "IDF error @ ("+oneSided[i]+","+tvalues[i][j]+") with "+degrees[j]+" degrees of freedom",
			    		      tvalues[i][j], t[j].idf(oneSided[i]), tvalues[i][j]*TERROR );	
			}
		}
	}
	
	@Test
	public void testTwoSided ()
	{
		StudentTDistribution t[] = new StudentTDistribution[degrees.length];
		
		for (int j=0; j<t.length; j++)
			t[j] =  new StudentTDistribution(degrees[j]);
		
		for (int i=0; i<tvalues.length; i++) {
			for (int j=0; j<t.length; j++) {
			    assertEquals ( "CDF error @ ("+twoSided[i]+","+tvalues[i][j]+") with "+degrees[j]+" degrees of freedom",
			    		       twoSided[i], t[j].cdf2(tvalues[i][j]), twoSided[i]*TERROR );	
			}
		}
	}

	@Test
	public void testTwoSidedInverse ()
	{
		StudentTDistribution t[] = new StudentTDistribution[degrees.length];
		
		for (int j=0; j<t.length; j++)
			t[j] =  new StudentTDistribution(degrees[j]);
		
		for (int i=0; i<tvalues.length; i++) {
			for (int j=0; j<t.length; j++) {
			    assertEquals ( "IDF error @ ("+twoSided[i]+","+tvalues[i][j]+") with "+degrees[j]+" degrees of freedom",
			    		       tvalues[i][j], t[j].idf2(twoSided[i]), tvalues[i][j]*TERROR );	
			}
		}
	}

}
