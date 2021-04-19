package org.osate.pluginsample.actions;

import java.util.ArrayList;

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

	public boolean add(SystemTypeImpl system) {
		boolean result = false;

		result = systems.add(system);
		result = systemNames.add(system.getName());

		return result;
	}

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

	public boolean add(PortConnectionImpl connection) {
		boolean result = false;

		result = connections.add(connection);
		result = connectionNames.add(connection.getName());

		return result;
	}

	public boolean add(SystemSubcomponentImpl subcomponent) {
		boolean result = false;

		result = subcomponents.add(subcomponent);
		result = subcomponentNames.add(subcomponent.getName());

		return result;
	}

	public boolean add(SystemImplementationImpl impl) {
		boolean result = false;

		result = implementations.add(impl);
		result = implementationNames.add(impl.getName());

		return result;
	}

	public boolean add(DefaultAnnexSubclauseImpl annex) {
		boolean result = false;

		result = annexes.add(annex);
		result = annexNames.add(annex.getName());

		return result;
	}

	public boolean remove(SystemTypeImpl system) {
		boolean result = false;

		result = systems.remove(system);
		result = systemNames.remove(system.getName());

		return result;
	}

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

	public boolean remove(PortConnectionImpl connection) {
		boolean result = false;

		result = connections.remove(connection);
		result = connectionNames.remove(connection.getName());

		return result;
	}

	public boolean remove(SystemSubcomponentImpl subcomponent) {
		boolean result = false;

		result = subcomponents.remove(subcomponent);
		result = subcomponentNames.remove(subcomponent.getName());

		return result;
	}

	public boolean remove(SystemImplementationImpl impl) {
		boolean result = false;

		result = implementations.remove(impl);
		result = implementationNames.remove(impl.getName());

		return result;
	}

	public boolean remove(DefaultAnnexSubclauseImpl annex) {
		boolean result = false;

		result = annexes.remove(annex);
		result = annexNames.remove(annex.getName());

		return result;
	}

	public SystemTypeImpl getSystem(int index) {
		return systems.get(index);
	}

	public DataPortImpl getInputFeature(int index) {
		return inputFeatures.get(index);
	}

	public DataPortImpl getOutputFeature(int index) {
		return outputFeatures.get(index);
	}

	public PortConnectionImpl getConnection(int index) {
		return connections.get(index);
	}

	public SystemSubcomponentImpl getSubcomponents(int index) {
		return subcomponents.get(index);
	}

	public SystemImplementationImpl getImpl(int index) {
		return implementations.get(index);
	}

	public DefaultAnnexSubclauseImpl getAnnex(int index) {
		return annexes.get(index);
	}

	public ArrayList<String> getSystemNames() {
		return systemNames;
	}

	public String getSystemName() {
		return systemNames.get(0);
	}

	public ArrayList<String> getInputFeatureTypes() {
		return inputFeatureTypes;
	}

	public ArrayList<String> getInputFeatureNames() {
		return inputFeatureNames;
	}

	public ArrayList<String> getOutputFeatureTypes() {
		return outputFeatureTypes;
	}

	public ArrayList<String> getOutputFeatureNames() {
		return outputFeatureNames;
	}

	public ArrayList<String> getConnectionNames() {
		return connectionNames;
	}

	public ArrayList<String> getSubcomponentNames() {
		return subcomponentNames;
	}

	public ArrayList<String> getImplNames() {
		return implementationNames;
	}

	public String getPortType(DataPortImpl port) {
		return port.getDataFeatureClassifier().getName();
	}

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
