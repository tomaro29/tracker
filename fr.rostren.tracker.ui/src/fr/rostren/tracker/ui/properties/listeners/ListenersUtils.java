package fr.rostren.tracker.ui.properties.listeners;

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

public final class ListenersUtils {
	private ListenersUtils() {
		// Do Nothing
	}

	public static void executeRemoveCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain = getEditingDomain(eObject);
		Command cmd = RemoveCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	public static void executeAddCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain = getEditingDomain(eObject);
		Command cmd = AddCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	public static void executeSetCommand(EObject eObject, EStructuralFeature feature, Object value) {
		EditingDomain editingDomain = getEditingDomain(eObject);
		Command cmd = SetCommand.create(editingDomain, eObject, feature, value);
		editingDomain.getCommandStack().execute(cmd);
	}

	public static EditingDomain getEditingDomain(EObject object) {
		Resource resource = object.eResource();
		if (resource == null)
			return null;

		IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) EcoreUtil.getExistingAdapter(resource,
				IEditingDomainProvider.class);
		if (editingDomainProvider != null)
			return editingDomainProvider.getEditingDomain();

		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet instanceof IEditingDomainProvider)
			return ((IEditingDomainProvider) resourceSet).getEditingDomain();
		if (resourceSet == null)
			return null;

		editingDomainProvider = (IEditingDomainProvider) EcoreUtil.getExistingAdapter(resourceSet,
				IEditingDomainProvider.class);
		if (editingDomainProvider != null)
			return editingDomainProvider.getEditingDomain();

		return null;
	}
}
