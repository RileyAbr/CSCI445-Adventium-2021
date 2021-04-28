package org.osate.pluginsample.actions;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.osate.aadl2.impl.SystemTypeImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.PortConnectionImpl;
import org.osate.aadl2.impl.SystemSubcomponentImpl;
import org.osate.aadl2.impl.SystemImplementationImpl;
import org.osate.aadl2.impl.DefaultAnnexSubclauseImpl;

public class IterationResultObject {
	private ArrayList<SystemTypeImpl> systems;
	private ArrayList<DataPortImpl> inputFeatures;
	private ArrayList<DataPortImpl> outputFeatures;
	private ArrayList<PortConnectionImpl> connections;
	private ArrayList<SystemSubcomponentImpl> subcomponents;
	private ArrayList<SystemImplementationImpl> implementations;
	private ArrayList<DefaultAnnexSubclauseImpl> annexes;

	private ArrayList<String> systemNames;
	private ArrayList<String> inputFeatureTypes;
	private ArrayList<String> inputFeatureNames;
	private ArrayList<String> outputFeatureTypes;
	private ArrayList<String> outputFeatureNames;
	private ArrayList<String> connectionNames;
	private ArrayList<String> subcomponentNames;
	private ArrayList<String> implementationNames;
	private ArrayList<String> annexNames;

	private String defaultDirectoryPath;
	private String storedPath;

	/**
	 * IterationResultObject constructor instantiating ArrayLists
	 */
	public IterationResultObject() {
		systems = new ArrayList<>();
		inputFeatures = new ArrayList<>();
		outputFeatures = new ArrayList<>();
		connections = new ArrayList<>();
		subcomponents = new ArrayList<>();
		implementations = new ArrayList<>();
		annexes = new ArrayList<>();

		systemNames = new ArrayList<>();

		inputFeatureTypes = new ArrayList<>();
		inputFeatureNames = new ArrayList<>();
		outputFeatureTypes = new ArrayList<>();
		outputFeatureNames = new ArrayList<>();
		connectionNames = new ArrayList<>();
		subcomponentNames = new ArrayList<>();
		implementationNames = new ArrayList<>();
		annexNames = new ArrayList<>();
	}

	/**
	 * add SystemTypeImpl to appropriate ArrayList
	 * 
	 * @param system
	 * @return result
	 */
	public boolean add(SystemTypeImpl system) {
		boolean result = false;

		result = systems.add(system);
		result = systemNames.add(system.getName());

		return result;
	}

	/**
	 * add DataPortImpl to appropriate ArrayList
	 * 
	 * @param feature
	 * @return result
	 */
	public boolean add(DataPortImpl feature) {
		boolean result = false;

		if (feature.isIn()) {
			result = inputFeatures.add(feature);
			result = inputFeatureTypes.add(feature.getDataFeatureClassifier().getName());
			result = inputFeatureNames.add(feature.getName());
		} else {
			result = outputFeatures.add(feature);
			result = outputFeatureTypes.add(feature.getDataFeatureClassifier().getName());
			result = outputFeatureNames.add(feature.getName());
		}

		return result;
	}

	/**
	 * add connection to appropriate ArrayList
	 * 
	 * @param connection
	 * @return result
	 */
	public boolean add(PortConnectionImpl connection) {
		boolean result = false;

		result = connections.add(connection);
		result = connectionNames.add(connection.getName());

		return result;
	}

	/**
	 * add system subcomponent to appropriate ArrayList
	 * 
	 * @param subcomponent
	 * @return result
	 */
	public boolean add(SystemSubcomponentImpl subcomponent) {
		boolean result = false;

		result = subcomponents.add(subcomponent);
		result = subcomponentNames.add(subcomponent.getName());

		return result;
	}

	/**
	 * add system implementation to appropriate ArrayList
	 * 
	 * @param impl
	 * @return result
	 */
	public boolean add(SystemImplementationImpl impl) {
		boolean result = false;

		result = implementations.add(impl);
		result = implementationNames.add(impl.getName());

		return result;
	}

	/**
	 * add annex to appropriate ArrayList
	 * 
	 * @param annex
	 * @return result
	 */
	public boolean add(DefaultAnnexSubclauseImpl annex) {
		boolean result = false;

		result = annexes.add(annex);
		result = annexNames.add(annex.getName());

		return result;
	}

	/**
	 * remove system from appropriate ArrayList
	 * 
	 * @param system
	 * @return result
	 */
	public boolean remove(SystemTypeImpl system) {
		boolean result = false;

		result = systems.remove(system);
		result = systemNames.remove(system.getName());

		return result;
	}

	/**
	 * remove feature from appropriate ArrayList
	 * 
	 * @param feature
	 * @return result
	 */
	public boolean remove(DataPortImpl feature) {
		boolean result = false;

		if (feature.isIn()) {
			result = inputFeatures.remove(feature);
			result = inputFeatureTypes.remove(feature.getDataFeatureClassifier().getName());
			result = inputFeatureNames.remove(feature.getName());
		} else {
			result = outputFeatures.remove(feature);
			result = outputFeatureTypes.remove(feature.getDataFeatureClassifier().getName());
			result = outputFeatureNames.remove(feature.getName());
		}

		return result;
	}

	/**
	 * remove connection from appropriate ArrayList
	 * 
	 * @param connection
	 * @return result
	 */
	public boolean remove(PortConnectionImpl connection) {
		boolean result = false;

		result = connections.remove(connection);
		result = connectionNames.remove(connection.getName());

		return result;
	}

	/**
	 * remove subcomponent from appropriate ArrayList
	 * 
	 * @param subcomponent
	 * @return result
	 */
	public boolean remove(SystemSubcomponentImpl subcomponent) {
		boolean result = false;

		result = subcomponents.remove(subcomponent);
		result = subcomponentNames.remove(subcomponent.getName());

		return result;
	}

	/**
	 * remove implementation from appropriate ArrayList
	 * 
	 * @param impl
	 * @return result
	 */
	public boolean remove(SystemImplementationImpl impl) {
		boolean result = false;

		result = implementations.remove(impl);
		result = implementationNames.remove(impl.getName());

		return result;
	}

	/**
	 * remove annex from appropriate ArrayList
	 * 
	 * @param annex
	 * @return result
	 */
	public boolean remove(DefaultAnnexSubclauseImpl annex) {
		boolean result = false;

		result = annexes.remove(annex);
		result = annexNames.remove(annex.getName());

		return result;
	}

	/**
	 * get system from index
	 * 
	 * @param index
	 * @return system
	 */
	public SystemTypeImpl getSystem(int index) {
		return systems.get(index);
	}

	/**
	 * get input feature from index
	 * 
	 * @param index
	 * @return input feature
	 */
	public DataPortImpl getInputFeature(int index) {
		return inputFeatures.get(index);
	}

	/**
	 * get output feature from index
	 * 
	 * @param index
	 * @return output feature
	 */
	public DataPortImpl getOutputFeature(int index) {
		return outputFeatures.get(index);
	}

	/**
	 * get connection from index
	 * 
	 * @param index
	 * @return connection
	 */
	public PortConnectionImpl getConnection(int index) {
		return connections.get(index);
	}

	/**
	 * get subcomponent from index
	 * 
	 * @param index
	 * @return subcomponent
	 */
	public SystemSubcomponentImpl getSubcomponents(int index) {
		return subcomponents.get(index);
	}

	/**
	 * get implementation from index
	 * 
	 * @param index
	 * @return implementation
	 */
	public SystemImplementationImpl getImpl(int index) {
		return implementations.get(index);
	}

	/**
	 * get annex from index
	 * 
	 * @param index
	 * @return annex
	 */
	public DefaultAnnexSubclauseImpl getAnnex(int index) {
		return annexes.get(index);
	}

	/**
	 * get system name ArrayList
	 * 
	 * @return systemNames
	 */
	public ArrayList<String> getSystemNames() {
		return systemNames;
	}

	/**
	 * get system name
	 * 
	 * @return system name
	 */
	public String getSystemName() {
		return systemNames.get(0);
	}

	/**
	 * get input feature types ArrayList
	 * 
	 * @return inputFeatureTypes
	 */
	public ArrayList<String> getInputFeatureTypes() {
		return inputFeatureTypes;
	}

	/**
	 * get input feature names ArrayList
	 * 
	 * @return inputFeatureNames
	 */
	public ArrayList<String> getInputFeatureNames() {
		return inputFeatureNames;
	}

	/**
	 * get output feature types ArrayList
	 * 
	 * @return outputFeatureTypes
	 */
	public ArrayList<String> getOutputFeatureTypes() {
		return outputFeatureTypes;
	}

	/**
	 * get output feature names ArrayList
	 * 
	 * @return outputFeatureNames
	 */
	public ArrayList<String> getOutputFeatureNames() {
		return outputFeatureNames;
	}

	/**
	 * get connection names ArrayList
	 * 
	 * @return connectionNames
	 */
	public ArrayList<String> getConnectionNames() {
		return connectionNames;
	}

	/**
	 * get subcomponent names ArrayList
	 * 
	 * @return subcomponentNames
	 */
	public ArrayList<String> getSubcomponentNames() {
		return subcomponentNames;
	}

	/**
	 * get implementation names ArrayList
	 * 
	 * @return implementationNames
	 */
	public ArrayList<String> getImplNames() {
		return implementationNames;
	}

	/**
	 * get type of a specific port
	 * 
	 * @param port
	 * @return data type of port
	 */
	public String getPortType(DataPortImpl port) {
		return port.getDataFeatureClassifier().getName();
	}

	public String getPath() {
		defaultDirectoryPath = System.getProperty("user.dir");
		storedPath = "";

		JFrame f = new JFrame();
		while (true) {
			storedPath = JOptionPane
					.showInputDialog(f,
							"Enter a directory path to locate and save previous assumptions and guarantees:\n"
									+ "Use the default directory path or enter a new directory path:",
							defaultDirectoryPath);
			File testFile = new File(storedPath);
			if (testFile.isDirectory()) {
				System.out.println("File is a directory");
				break;
			} else {
				JOptionPane.showMessageDialog(f, "Something is wrong with your path.\n "
						+ "Please specify a new path for your stored assumptions and guarantees file.");
				System.out.println("Directory doesn't exist");
			}
		}
		String path = storedPath;

		return path;
	}

	/**
	 * Create String containing all names of iteration results
	 * 
	 * @return result
	 */
	@Override
	public String toString() {
		String result = "";

		for (SystemTypeImpl system : systems) {
			result += "System: " + system.getName() + "\n";
		}

		for (DataPortImpl feature : inputFeatures) {
			result += "Input Feature: " + feature.getName() + ", " + feature.getDataFeatureClassifier().getName()
					+ "\n";
		}

		for (DataPortImpl feature : outputFeatures) {
			result += "Output Feature: " + feature.getName() + ", " + feature.getDataFeatureClassifier().getName()
					+ "\n";
		}

		for (PortConnectionImpl connection : connections) {
			result += "Connection: " + connection.getName() + "\n";
		}

		for (SystemSubcomponentImpl subcomponent : subcomponents) {
			result += "Subcomponent: " + subcomponent.getName() + "\n";
		}

		for (SystemImplementationImpl impl : implementations) {
			result += "System Implmentation: " + impl.getName() + "\n";
		}

		for (DefaultAnnexSubclauseImpl annex : annexes) {
			result += "Annex: " + annex.getSourceText() + "\n";
		}

		return result;
	}
}
