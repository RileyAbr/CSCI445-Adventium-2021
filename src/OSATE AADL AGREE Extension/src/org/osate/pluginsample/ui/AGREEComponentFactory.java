package org.osate.pluginsample.ui;

import java.util.Random;

public class AGREEComponentFactory {
	private static String[] parameters = {"temperature", "degrees", "weight", "height", "PSI" };
	private static String[] assumptionComparators = {">", ">=", "=", "=<", "<"};
	private static String[] guaranteeComparators = {"=", "->", "=>"};
	
	public static String createParameter() {
		Random rand = new Random();
		
		return parameters[rand.nextInt(parameters.length)];
	}
	
	public static int createComparisonValue() {
		Random rand = new Random();
		
		return rand.nextInt(9) * 10 * (10 * rand.nextInt(1));
	}
	
	public static String createAssumptionComparator() {
		Random rand = new Random();
		
		return parameters[rand.nextInt(assumptionComparators.length)];
	}
	
	public static String createGuaranteeComparators() {
		Random rand = new Random();
		
		return parameters[rand.nextInt(guaranteeComparators.length)];
	}
}
