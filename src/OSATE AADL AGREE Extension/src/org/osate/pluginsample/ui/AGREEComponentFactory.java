package org.osate.pluginsample.ui;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AGREEComponentFactory {
	private static String[] mockAssumptionParameters = {"temperature", "degrees", "weight", "height", "PSI" };
	private static String[] mockGuaranteeParameters = {"shutdown", "alert", "notify", "restart", "email"};
	private static String[] inputIntegerComparators = {">", ">=", "=", "=<", "<"};
	private static String[] inputBooleanComparators = {"==", "!="};
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
	
	
	public static String getInputIntegerComparator() {
		Random rand = new Random();
		
		return inputIntegerComparators[rand.nextInt(inputIntegerComparators.length)];
	}
	
	public static String[] getInputIntegerComparators() {
		return inputIntegerComparators;
	}
	
	public static String[] getInputBooleanComparators() {
		return inputBooleanComparators;
	}
	
	public static String getMockGuaranteeComparator() {
		Random rand = new Random();
		
		return guaranteeComparators[rand.nextInt(guaranteeComparators.length)];
	}
	
	public static String[] getAllGuaranteeComparators() {
		return guaranteeComparators;
	}
	
	public static ArrayList<String> getMockAssumptionStatements() {
		int mockAssumptionCount = 3;
		ArrayList<String> mockAssumptions = new ArrayList<String>();
		
		for (int i = 0; i < mockAssumptionCount; i++) {
			mockAssumptions.add(String.format("assume \"Sample assumption\" : (%s %s %d)", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getInputIntegerComparator(), AGREEComponentFactory.getMockComparisonValue()));		
		}
		
		return mockAssumptions;
	}
	
	public static ArrayList<String> getMockGuaranteeStatements() {
		int mockGuaranteeCount = 4;
		ArrayList<String> mockGuarantees = new ArrayList<String>();
		
		for (int i = 0; i < mockGuaranteeCount; i++) {
        	mockGuarantees.add(String.format("guarantee \"Example guarantee\" : (%s %s %d) %s %s", AGREEComponentFactory.getMockAssumptionParameter(), AGREEComponentFactory.getInputIntegerComparator(), AGREEComponentFactory.getMockComparisonValue(), AGREEComponentFactory.getMockGuaranteeComparator(), AGREEComponentFactory.getMockGuaranteeParameter()));
		}
		
		return mockGuarantees;
	}
	
	// Reads previously stored assumptions and puts them in an ArrayList
	public static ArrayList<String> getPreviouslyStoredAssumptionStatements() {
		ArrayList<String> assumptions = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\ansle\\OneDrive\\Documents\\GitHub\\CSCI445-Adventium-2021\\src\\OSATE AADL AGREE Extension\\src\\org\\osate\\pluginsample\\ui\\StoredAssumGuarants.txt"));
			String line = reader.readLine();
			System.out.println("Previously Stored Assumptions and Guarantees\n");
			while (line != null) {
				if (line.contains("assume")) {
					String prevAssump = line.replace("\t", "");
					assumptions.add(prevAssump);
					System.out.println("Assumption is : " + prevAssump);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("Error Occurred\n");
		}
		return assumptions;
	}
	
	// Reads previously stored guarantees and puts them in an ArrayList
	public static ArrayList<String> getPreviouslyStoredGuaranteesStatements() {
		ArrayList<String> guarantees = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"C:\\Users\\ansle\\OneDrive\\Documents\\GitHub\\CSCI445-Adventium-2021\\src\\OSATE AADL AGREE Extension\\src\\org\\osate\\pluginsample\\ui\\StoredAssumGuarants.txt"));
			String line = reader.readLine();
			System.out.println("Previously Stored Assumptions and Guarantees\n");
			while (line != null) {
				if (line.contains("guarantee")) {
					String prevGuarants = line.replace("\t", "");
					guarantees.add(prevGuarants);
					System.out.println("Guarantees is : " + prevGuarants);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("Error Occurred\n");
		}
		return guarantees;
	}
}
