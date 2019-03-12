package com.portfolio.management.portfolio_analyser;

/*
 *  POJO class for an investment portfolio. 
 */
public class Portfolio {
	// Unique name for the portfolio.
    public String name;
    
    // Mean return rate of the portfolio.
    public float meanReturnRate;
    
    // Standard deviation of the return rate of the portfolio.
    public float stdReturnRate;

    public Portfolio(String name, float meanReturnRate, float stdReturnRate) {
      this.name = name;
      this.meanReturnRate = meanReturnRate;
      this.stdReturnRate = stdReturnRate;
    }

    public String toString() {
      return "Portfolio name: " + name + ". Mean return rate: " + meanReturnRate + ". Standard deviation return rate: " + stdReturnRate;
    }

}
