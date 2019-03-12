package com.portfolio.management.portfolio_analyser;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for Portfolio analyzer.
 */
public class PortfolioAnalyzerTest {
	@Test
	public void TestRunAnalysisForAggressivePortfolio() {
		// Initialize the portfolio.
		float principal = 100000;
		int numYears = 20;
		int numSimulations = 10000;
		float inflationRate = 3;
		Portfolio aggressivePortfolio = new Portfolio("aggressive", 9.4324f, 15.675f);

		// Analyze the portfolio.
		PortfolioAnalyzer portfolioAnalyzer = new PortfolioAnalyzer();
		PortfolioAnalysisResults aggressivePortfolioAnalysisResults = portfolioAnalyzer.RunAnalysis(principal,
				aggressivePortfolio, numYears, false, numSimulations, inflationRate);

		// Compare expected and actual results.
		Assert.assertEquals(278671.15625f, aggressivePortfolioAnalysisResults.medianReturns, 0.01);
		Assert.assertEquals(631682.0f, aggressivePortfolioAnalysisResults.tenthPercentileBestCase, 0.01);
		Assert.assertEquals(118690.859375f, aggressivePortfolioAnalysisResults.tenthPercentileWorstCase, 0.01);
	}

	@Test
	public void TestRunAnalysisForVeryConservativePortfolio() {
		// Initialize the portfolio.
		float principal = 100000;
		int numYears = 20;
		int numSimulations = 10000;
		float inflationRate = 3;
		Portfolio veryConservativePortfolio = new Portfolio("very conservative", 6.189f, 6.3438f);

		// Analyze the portfolio.
		PortfolioAnalyzer portfolioAnalyzer = new PortfolioAnalyzer();
		PortfolioAnalysisResults veryConservativePortfolioAnalysisResults = portfolioAnalyzer.RunAnalysis(principal,
				veryConservativePortfolio, numYears, false, numSimulations, inflationRate);

		// Compare expected and actual results.
		Assert.assertEquals(178964.375f, veryConservativePortfolioAnalysisResults.medianReturns, 0.01);
		Assert.assertEquals(250483.6875f, veryConservativePortfolioAnalysisResults.tenthPercentileBestCase, 0.01);
		Assert.assertEquals(126769.5390625f, veryConservativePortfolioAnalysisResults.tenthPercentileWorstCase, 0.01);
	}
}
