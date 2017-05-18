/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;

public final class DomainUtils {
	/**
	 * Constructor
	 */
	private DomainUtils() {
		// Do Nothing
	}

	/**
	 * Executes the remove command
	 * @param eObject the given eobject
	 * @param feature the given feature
	 * @param value the value
	 */
	public static void executeRemoveCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain=getEditingDomain(eObject);
		Command cmd=RemoveCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	/**
	 * Executes the add command
	 * @param eObject the given eobject
	 * @param feature the given feature
	 * @param value the value
	 */
	public static void executeAddCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain=getEditingDomain(eObject);
		Command cmd=AddCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	/**
	 * Executes the set command
	 * @param eObject the given eobject
	 * @param feature the given feature
	 * @param value the value
	 */
	public static void executeSetCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain=getEditingDomain(eObject);
		Command cmd=SetCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	/**
	 * Returns the editing domain
	 * @param object the given object
	 * @return the editing domain
	 */
	public static EditingDomain getEditingDomain(EObject object) {
		Resource resource=object.eResource();
		if (resource == null) {
			return null;
		}

		IEditingDomainProvider editingDomainProvider=(IEditingDomainProvider)EcoreUtil.getExistingAdapter(resource, IEditingDomainProvider.class);
		if (editingDomainProvider != null) {
			return editingDomainProvider.getEditingDomain();
		}

		ResourceSet resourceSet=resource.getResourceSet();
		if (resourceSet instanceof IEditingDomainProvider) {
			return ((IEditingDomainProvider)resourceSet).getEditingDomain();
		}
		if (resourceSet == null) {
			return null;
		}

		editingDomainProvider=(IEditingDomainProvider)EcoreUtil.getExistingAdapter(resourceSet, IEditingDomainProvider.class);
		if (editingDomainProvider != null) {
			return editingDomainProvider.getEditingDomain();
		}

		return null;
	}
}
