package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Compute {

	public static void computeStatistics(double[] numbers) {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (double num : numbers) {
			stats.addValue(num);
		}
		double mean = stats.getMean();
		double standardDeviation = stats.getStandardDeviation();
		double variance = stats.getVariance();
		System.out.println("Średnia: " + mean);
		System.out.println("Odchylenie standardowe: " + standardDeviation);
		System.out.println("Wariancja: " + variance);
	}

	public static void main(String[] args) {
		double[] numbers = {1.2, 3.4, 5.6, 7.8, 9.0};
		computeStatistics(numbers);
	}
}
