/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.tests;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.rostren.tracker.TrackerPackage;

public class TestUtils {

	/**
	 * Returns the model instance as {@link EObject} instance.
	 * @param testModelPath the model path to load
	 * @return the {@link EObject} model instance
	 * @throws IOException an {@link IOException}
	 */
	public static EObject load(String testModelPath) throws IOException {
		ResourceSet resourceSet=new ResourceSetImpl();
		EPackage.Registry.INSTANCE.put(TrackerPackage.eNS_URI, TrackerPackage.eINSTANCE);

		Map<String, Object> options=resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		options.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.setPackageRegistry(TrackerPackage.Registry.INSTANCE);
		URI uri=URI.createFileURI(testModelPath);

		Resource resource=resourceSet.getResource(uri, true);
		resource.load(Collections.EMPTY_MAP);
		return resource.getContents().get(0);
	}
}
