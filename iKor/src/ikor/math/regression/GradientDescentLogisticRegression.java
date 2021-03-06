package ikor.math.regression;

// Title:       Gradient descent logistic regression
// Version:     2.0
// Copyright:   2012-2014
// Author:      Fernando Berzal
// E-mail:      berzal@acm.org

import ikor.math.Matrix;
import ikor.math.MatrixFactory;
import ikor.math.Vector;

/**
 * Logistic regression using the gradient descent method for parameter estimation
 * 
 * @author Fernando Berzal (berzal@acm.org)
 */
public class GradientDescentLogisticRegression extends GradientDescentRegression 
{
	// Constructors
	
	public GradientDescentLogisticRegression (Vector[] x, Vector y)
	{
		super(x,y);
	}

	public GradientDescentLogisticRegression (double[][] x, double[] y)
	{
		super(x,y);
	}
	
	
	// Regression model
	
	public RegressionModel createModel ()
	{
		return new LogisticRegressionModel(variables());
	}
		
	
	// Cost function
	
	@Override
	public double cost()
	{
		double J = 0;
		
		for (int i=0; i<instances(); i++) {
			J += cost(i);
		}
		
		return J / instances();
	}
	
	
	public double cost (int i)
	{
		double y = getY(i);
		double h = getPrediction(getModel(),i);
		
		return -y*Math.log(h)-(1-y)*Math.log(1-h);
	}
	
	
	public double[] gradient ()
	{
		RegressionModel model = getModel();
		int      m = instances();
		int      p = model.parameters();
		double[] s = new double[p];
		double   r;
		int      i,j;
		
		for (i=0; i<m; i++) {
			
			r = getPrediction(model,i) - getY(i);
		
			for (j=0; j<p; j++) {
				s[j] += r * getX(j,i);
			}
		}

		for (j=0; j<s.length; j++) {
			s[j] /= m;
		}
		
		return s;
	}
	
	
	public double[][] hessian ()
	{
		RegressionModel model = getModel();
		int        m = instances();
		int        p = model.parameters();
		double[][] s = new double[p][p];
		double     h,f;
		int        i,j,k;
		
		for (i=0; i<m; i++) {
			
			h = getPrediction(model,i);
		    f = h*(1-h);
		    
			for (j=0; j<p; j++) {
				for (k=0; k<p; k++) {
					s[j][k] += f * getX(j,i) * getX(k,i);
				}
			}
		}

		for (j=0; j<p; j++) {
			for (k=0; k<p; k++) {
				s[j][k] /= m;
			}
		}
		
		return s;
	}
	
	// Estimate of the covariance matrix
	
	public Matrix covariance ()
	{
		Matrix hessian = MatrixFactory.create(hessian());                  
		
		return hessian.inverse().divide(instances());
	}
}
