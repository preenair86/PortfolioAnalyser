package com.portfolio.management.portfolio_analyser;

import java.util.*;

/**
 * Portfolio analyzer class which simulates the return rates of a portfolio, and
 * computes return percentiles.
 *
 */
public class PortfolioAnalyzer {
	public static final long RANDOM_SEED = 1;

	private Random random = new Random(RANDOM_SEED);

	/**
	 * Computes return rate for the input portfolio.
	 * 
	 * @param portfolio
	 *            whose return rate is to be computed.
	 * @return return rate for the portfolio.
	 */
	private float getReturnRate(Portfolio portfolio) {
		return (float) (random.nextGaussian() * portfolio.stdReturnRate + portfolio.meanReturnRate);
	}

	/**
	 * Computes returns for a portfolio given the initial principal invested,
	 * portfolio, and number of years of investment. Return is normalized based
	 * on the inflation rate.
	 * 
	 * @param principal
	 *            principal invested.
	 * @param portfolio
	 *            portfolio whose returns are to be calculated.
	 * @param numYears
	 *            number of years of the investment.
	 * @param fixedReturnEachYear
	 *            whether to use a single return rate for all the investing
	 *            years, or to compute a new return rate each year.
	 * @param inflationRate
	 *            rate of inflation per year.
	 * @return Inflation adjusted return of the portfolio.
	 */
	private float computeInflationAdjustedReturns(float principal, Portfolio portfolio, int numYears,
			boolean fixedReturnEachYear, float inflationRate) {
		if (fixedReturnEachYear) {
			float fixedReturnRate = getReturnRate(portfolio);
			return (float) (principal * Math.pow((1 + fixedReturnRate / 100), numYears)
					/ Math.pow((1 + inflationRate / 100), numYears));
		}
		// Return rate is different for each year.
		float totalReturn = principal;
		for (int i = 0; i < numYears; ++i) {
			totalReturn = totalReturn * (1 + getReturnRate(portfolio) / 100) / (1 + inflationRate / 100);
		}
		return totalReturn;
	}

	/**
	 * Analyzes the portfolio, and computes the median, 10th percentile best
	 * case and 10th percentile worst case returns of the portfolio.
	 * 
	 * @param principal
	 *            principal invested.
	 * @param portfolio
	 *            portfolio to be analyzed.
	 * @param numYears
	 *            number of years invested
	 * @param fixedReturnEachYear
	 *            whether to use a single return rate for all the investing
	 *            years, or to compute a new return rate each year.
	 * @param numSimulations
	 *            number of simulations to be run.
	 * @param inflationRate
	 *            rate of inflation per year.
	 * @return median, 10th percentile best case and 10th percentile worst case
	 *         returns of the portfolio.
	 */
	public PortfolioAnalysisResults RunAnalysis(float principal, Portfolio portfolio, int numYears,
			boolean fixedReturnEachYear, int numSimulations, float inflationRate) {
		List<Float> simulationResults = new ArrayList<Float>(numSimulations);
		for (int i = 0; i < numSimulations; ++i) {
			simulationResults.add(computeInflationAdjustedReturns(principal, portfolio, numYears, fixedReturnEachYear,
					inflationRate));
		}
		Collections.sort(simulationResults);
		float medianResults = simulationResults.get((int) (numSimulations * 0.5));
		float tenthPercentileWorstCaseResults = simulationResults.get((int) (numSimulations * 0.1));
		float tenthPercentileBestCaseResults = simulationResults.get((int) (numSimulations * 0.9));
		PortfolioAnalysisResults results = new PortfolioAnalysisResults(portfolio, medianResults,
				tenthPercentileWorstCaseResults, tenthPercentileBestCaseResults);
		return results;
	}

	public static void main(String[] args) {
	    float principal = 100000;
	    int numYears = 20;
	    int numSimulations = 10000;
	    float inflationRate = 3;
		Portfolio aggressivePortfolio = new Portfolio("aggressive", 9.4324f, 15.675f);
		Portfolio veryConservativePortfolio = new Portfolio("very conservative", 6.189f, 6.3438f);
	    
	    PortfolioAnalyzer portfolioAnalyser = new PortfolioAnalyzer();
	    PortfolioAnalysisResults aggressivePortfolioAnalysisResults = portfolioAnalyser.RunAnalysis(principal, aggressivePortfolio, numYears, false, numSimulations, inflationRate);
	    System.out.println(aggressivePortfolioAnalysisResults.toString());
		PortfolioAnalysisResults veryConservativePortfolioAnalysisResults = portfolioAnalyser.RunAnalysis(principal, veryConservativePortfolio, numYears, false, numSimulations, inflationRate);
		System.out.println(veryConservativePortfolioAnalysisResults.toString());
	  }
}
