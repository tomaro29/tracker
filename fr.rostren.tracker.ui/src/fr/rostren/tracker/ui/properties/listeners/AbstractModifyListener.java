package fr.rostren.tracker.ui.properties.listeners;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Widget;

public abstract class AbstractModifyListener implements ModifyListener {

    @Override
    public void modifyText(ModifyEvent event) {
	Widget widget = event.widget;
	if (widget == null || widget.isDisposed())
	    return;

	executeModifyText(widget);
    }

    abstract protected void executeModifyText(Widget widget);

    protected void executeSetCommand(EObject eObject, EStructuralFeature feature, Object value) {
	EditingDomain editingDomain = getEditingDomain(eObject);
	Command cmd = SetCommand.create(editingDomain, eObject, feature, value);
	editingDomain.getCommandStack().execute(cmd);
    }

    protected EditingDomain getEditingDomain(EObject object) {
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
