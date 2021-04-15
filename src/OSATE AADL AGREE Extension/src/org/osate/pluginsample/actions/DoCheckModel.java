/**
 * <copyright>
 * Copyright  2013 by Carnegie Mellon University, all rights reserved.
 * 
 * Use of the Open Source AADL Tool Environment (OSATE) is subject to the terms of the license set forth
 * at http://www.eclipse.org/org/documents/epl-v10.html.
 * 
 * NO WARRANTY
 * 
 * ANY INFORMATION, MATERIALS, SERVICES, INTELLECTUAL PROPERTY OR OTHER PROPERTY OR RIGHTS GRANTED OR PROVIDED BY
 * CARNEGIE MELLON UNIVERSITY PURSUANT TO THIS LICENSE (HEREINAFTER THE ''DELIVERABLES'') ARE ON AN ''AS-IS'' BASIS.
 * CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, INFORMATIONAL CONTENT,
 * NONINFRINGEMENT, OR ERROR-FREE OPERATION. CARNEGIE MELLON UNIVERSITY SHALL NOT BE LIABLE FOR INDIRECT, SPECIAL OR
 * CONSEQUENTIAL DAMAGES, SUCH AS LOSS OF PROFITS OR INABILITY TO USE SAID INTELLECTUAL PROPERTY, UNDER THIS LICENSE,
 * REGARDLESS OF WHETHER SUCH PARTY WAS AWARE OF THE POSSIBILITY OF SUCH DAMAGES. LICENSEE AGREES THAT IT WILL NOT
 * MAKE ANY WARRANTY ON BEHALF OF CARNEGIE MELLON UNIVERSITY, EXPRESS OR IMPLIED, TO ANY PERSON CONCERNING THE
 * APPLICATION OF OR THE RESULTS TO BE OBTAINED WITH THE DELIVERABLES UNDER THIS LICENSE.
 * 
 * Licensee hereby agrees to defend, indemnify, and hold harmless Carnegie Mellon University, its trustees, officers,
 * employees, and agents from all claims or demands made against them (and any related losses, expenses, or
 * attorney's fees) arising out of, or relating to Licensee's and/or its sub licensees' negligent use or willful
 * misuse of or negligent conduct or willful misconduct regarding the Software, facilities, or other rights or
 * assistance granted by Carnegie Mellon University under this License, including, but not limited to, any claims of
 * product liability, personal injury, death, damage to property, or violation of any laws or regulations.
 * 
 * Carnegie Mellon University Software Engineering Institute authored documents are sponsored by the U.S. Department
 * of Defense under Contract F19628-00-C-0003. Carnegie Mellon University retains copyrights in all material produced
 * under this contract. The U.S. Government retains a non-exclusive, royalty-free license to publish or reproduce these
 * documents, or allow others to do so, for U.S. Government purposes only pursuant to the copyright license
 * under the contract clause at 252.227.7013.
 * </copyright>
 * 
 */
package org.osate.pluginsample.actions;

import org.osate.pluginsample.ui.AGREEComponentFactory;
import org.osate.pluginsample.ui.GUMBOInterface;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.xtext.nodemodel.impl.CompositeNodeWithSemanticElement;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Comment;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Feature;
import org.osate.aadl2.PortCategory;
import org.osate.aadl2.impl.AnnexSubclauseImpl;
import org.osate.aadl2.impl.ConnectedElementImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DefaultAnnexSubclauseImpl;
import org.osate.aadl2.impl.FeatureClassifierImpl;
import org.osate.aadl2.impl.FeatureGroupImpl;
import org.osate.aadl2.impl.FeatureImpl;
import org.osate.aadl2.impl.PackageRenameImpl;
import org.osate.aadl2.impl.PortConnectionImpl;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.impl.AadlPackageImpl;
import org.osate.aadl2.impl.AbstractTypeImpl;
import org.osate.aadl2.impl.PublicPackageSectionImpl;
import org.osate.aadl2.impl.RealizationImpl;
import org.osate.aadl2.impl.SystemTypeImpl;
import org.osate.aadl2.impl.SystemImplementationImpl;
import org.osate.aadl2.impl.SystemSubcomponentImpl;
import org.osate.pluginsample.Activator;
import org.osate.pluginsample.CheckModel;
import org.osate.ui.dialogs.Dialog;
import org.osate.ui.handlers.AaxlReadOnlyHandlerAsJob;
import org.osate.ui.handlers.AadlFileTypePropertyTester;
import org.osgi.framework.Bundle;

public final class DoCheckModel extends AaxlReadOnlyHandlerAsJob {

	private IterationResultObject iro;

	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	protected String getMarkerType() {
		return "org.osate.pluginsample";
	}

	protected String getActionName() {
		return "Check the AADL model";
	}

	private void searchComponents(EList<EObject> contents) {
		if (contents.size() == 0)
			return;

		for (int i = 0; i < contents.size(); i++) {
			Object check = contents.get(i);
			if (check instanceof SystemTypeImpl) {
				SystemTypeImpl current = (SystemTypeImpl) check;
				iro.add(current);
				searchComponents(current.eContents());
			} else if (check instanceof DataPortImpl) {
				DataPortImpl current = (DataPortImpl) check;
				iro.add(current);
				searchComponents(current.eContents());
			} else if (check instanceof PortConnectionImpl) {
				PortConnectionImpl current = (PortConnectionImpl) check;
				iro.add(current);
				searchComponents(current.eContents());
			} else if (check instanceof SystemSubcomponentImpl) {
				SystemSubcomponentImpl current = (SystemSubcomponentImpl) check;
				iro.add(current);
				searchComponents(current.eContents());
			} else if (check instanceof SystemImplementationImpl) {
				SystemImplementationImpl current = (SystemImplementationImpl) check;
				iro.add(current);
				searchComponents(current.eContents());
			} else if (check instanceof DefaultAnnexSubclauseImpl) {
				DefaultAnnexSubclauseImpl current = (DefaultAnnexSubclauseImpl) check;

				String firstChars = current.getSourceText().substring(0, 3);
				if (firstChars.equals("{**")) {
					iro.add(current);
				}

				searchComponents(current.eContents());
			}
		}
	}

	public void doAaxlAction(IProgressMonitor monitor, Element obj) {
		if (obj instanceof AadlPackageImpl)
			doAadlAction(monitor, obj);
	}

	public void doAadlAction(IProgressMonitor monitor, Element obj) {
		AadlPackageImpl api = null;
		iro = new IterationResultObject();

		CheckModel validator;

		monitor.beginTask("Check the AADL model", IProgressMonitor.UNKNOWN);

		validator = new CheckModel(monitor, getErrorManager());

		if (obj instanceof AadlPackageImpl) {
			api = (AadlPackageImpl) obj;
		} else {
			api = null;
		}

		if (api != null) {
			PublicPackageSectionImpl base = (PublicPackageSectionImpl) api.eContents().get(0);
			EList<EObject> baseContents = (EList<EObject>) base.eContents();

			searchComponents(baseContents);

			String[] inputFeatures = iro.getInputFeatureNames().toArray(new String[0]);
			String[] inputFeaturesTypes = iro.getInputFeatureTypes().toArray(new String[0]);
			String[] outputFeatures = iro.getOutputFeatureNames().toArray(new String[0]);
			String[] outputFeaturesTypes = iro.getOutputFeatureTypes().toArray(new String[0]);

			ArrayList<String> previousAssumptions = AGREEComponentFactory.getPreviouslyStoredAssumptionStatements();
			ArrayList<String> previousGuarantees = AGREEComponentFactory.getPreviouslyStoredGuaranteesStatements();
			new GUMBOInterface(inputFeatures, inputFeaturesTypes, outputFeatures, outputFeaturesTypes,
					previousAssumptions, previousGuarantees);
		} else {
			Dialog.showInfo("Analysis result", "Please choose an AADL model");
		}
			
		monitor.done();
	}

}
