package com.portfolio.management.portfolio_analyser;

/*
 *  POJO class storing the portfolio and its analysis results.
 */
public class PortfolioAnalysisResults {
	// Portfolio being analyzed.
    public Portfolio portfolio;
    
    // Investment made in the portfolio.
    public float investment;
    
    // Number of years the investment was made.
    public int numYears;
    
    // Median returns of the investment after the investment period, normalized based on inflation rate.
    public float medianReturns;
    
    // Tenth percentile worse case returns of the investment after the investment period, normalized based on inflation rate.
    public float tenthPercentileWorstCase;
    
    // Tenth percentile best case returns of the investment after the investment period, normalized based on inflation rate.
    public float tenthPercentileBestCase;

    public PortfolioAnalysisResults(Portfolio portfolio, float medianReturns, float tenthPercentileWorstCase, float tenthPercentileBestCase) {
      this.portfolio = portfolio;
      this.medianReturns = medianReturns;
      this.tenthPercentileWorstCase = tenthPercentileWorstCase;
      this.tenthPercentileBestCase = tenthPercentileBestCase;
    }

    public String toString() {
      return portfolio.toString() + ". Median returns is " + medianReturns + ". 10% best case returns is " + tenthPercentileBestCase + ". 10% worst case returns is " + tenthPercentileWorstCase;
    }

}
