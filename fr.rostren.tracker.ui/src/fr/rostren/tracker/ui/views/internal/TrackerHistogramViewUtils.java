/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2017, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.ui.views.internal;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.TrackerPackage;
import fr.rostren.tracker.presentation.TrackerEditor;

public final class TrackerHistogramViewUtils {
	/**
	 * Private constructor
	 * Prevent public access to utilities final class
	 */
	private TrackerHistogramViewUtils() {
		// Do Nothing
	}

	/**
	 * Returns the current tracker object
	 * @return the current tracker object
	 */
	public static Tracker getTracker() {
		IEditorPart editorPart=getEditorPart();
		if (!(editorPart instanceof TrackerEditor)) {
			return null;
		}
		TrackerEditor editor=(TrackerEditor)editorPart;
		ISelection selection=editor.getSelection();
		if (!(selection instanceof StructuredSelection)) {
			return null;
		}
		if (!selection.isEmpty()) {
			StructuredSelection ss=(StructuredSelection)selection;
			Object firstElement=ss.getFirstElement();
			if (firstElement instanceof XMIResourceImpl) {
				return (Tracker)((XMIResourceImpl)firstElement).getContents().get(0);
			}
		}
		URI uri=URI.createFileURI(((FileEditorInput)editorPart.getEditorInput()).getPath().toOSString());
		Resource resource=loadResource(uri);
		return (Tracker)resource.getContents().get(0);
	}

	/**
	 * Returns the current active editor part
	 * @return the current active editor part
	 */
	private static IEditorPart getEditorPart() {
		IWorkbenchWindow activeWorkbenchWindow=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage=activeWorkbenchWindow.getActivePage();

		if (activePage == null || activePage.getActiveEditor() == null) {
			return null;
		}
		return activePage.getActiveEditor();
	}

	/**
	 * Loads the resource
	 * @param uri the resource {@link URI} instance
	 * @return the loaded resource
	 */
	private static Resource loadResource(URI uri) {
		ResourceSet resourceSet=new ResourceSetImpl();

		EPackage.Registry.INSTANCE.put(TrackerPackage.eNS_URI, TrackerPackage.eINSTANCE);

		Map<String, Object> options=resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		options.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.setPackageRegistry(TrackerPackage.Registry.INSTANCE);

		return resourceSet.getResource(uri, true);
	}
}
