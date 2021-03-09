package org.osate.pluginsample.ui;

import java.util.Random;

public class AGREEComponentFactory {
	private static String[] mockAssumptionParameters = {"temperature", "degrees", "weight", "height", "PSI" };
	private static String[] mockGuaranteeParameters = {"shutdown", "alert", "notify", "restart", "email"};
	private static String[] assumptionComparators = {">", ">=", "=", "=<", "<"};
	private static String[] guaranteeComparators = {"=", "->", "=>"};
	
	public static String getMockAssumptionParameter() {
		Random rand = new Random();
		
		return mockAssumptionParameters[rand.nextInt(mockAssumptionParameters.length)];
	}
	
	public static String[] getAllMockAssumptionParameters() {
		return mockAssumptionParameters;
	}
	
	public static String getMockGuaranteeParameter() {
		Random rand = new Random();
		
		return mockGuaranteeParameters[rand.nextInt(mockGuaranteeParameters.length)];
	}
	
	public static String[] getAllMockGuaranteeParameters() {
		return mockGuaranteeParameters;
	}
	
	public static int getMockComparisonValue() {
		Random rand = new Random();
		
		return (rand.nextInt(9) + 1) * 10 * (10 / (rand.nextInt(1) + 1));
	}
	
	public static String getMockAssumptionComparator() {
		Random rand = new Random();
		
		return assumptionComparators[rand.nextInt(assumptionComparators.length)];
	}
	
	public static String[] getAllAssumptionComparators() {
		return assumptionComparators;
	}
	
	public static String getMockGuaranteeComparator() {
		Random rand = new Random();
		
		return guaranteeComparators[rand.nextInt(guaranteeComparators.length)];
	}
	
	public static String[] getAllGuaranteeComparators() {
		return guaranteeComparators;
	}
	
	public static String[] getMockAssumptionStatements() {
		int mockAssumptionCount = 3;
		String[] mockAssumptions = new String[mockAssumptionCount];
		
		for (int i = 0; i < mockAssumptionCount; i++) {
			mockAssumptions[i] = String.format("assume \"Sample assumption\" : (%s %s %d)", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue());		
		}
		
		return mockAssumptions;
	}
	
	public static String[] getMockGuaranteeStatements() {
		int mockGuaranteeCount = 4;
		String[] mockGuarantees = new String[mockGuaranteeCount];
		
		for (int i = 0; i < mockGuaranteeCount; i++) {
        	mockGuarantees[i] = String.format("guarntee \"Example guarantee\" : (%s %s %d) %s %s", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getMockAssumptionComparator(), AGREEComponentFactory.getMockComparisonValue(), AGREEComponentFactory.getMockGuaranteeComparator(), AGREEComponentFactory.getMockGuaranteeParameter());
		}
		
		return mockGuarantees;
	}
}
