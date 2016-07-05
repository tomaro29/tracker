package fr.rostren.tracker.ui.properties.sections.operation;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.comparators.OperationTitleComparator;
import fr.rostren.tracker.ui.properties.content.comparators.OriginComparator;
import fr.rostren.tracker.ui.properties.listeners.OperationAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OperationAttributesPropertySection extends AbstractAttributesPropertySection {
	protected CCombo titleCombo;
	protected CCombo originCombo;

	private final ModifyListener listener=new OperationAttributesModifyListener(this);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		titleCombo=createLabeledCombo(body, null, "Title:"); //$NON-NLS-1$
		originCombo=createLabeledCombo(body, titleCombo, "Origin:"); //$NON-NLS-1$
		addListeners();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		disposeListeners();
		OperationTitle operationTitle=getOperationTitle();
		titleCombo.setItems(getOperationTitlesItems());
		if (operationTitle != null) {
			titleCombo.setItem(getOperationTitleItemIndex(operationTitle), operationTitle.getTitle());
		}
		originCombo.setItems(getOriginsItems());
		Origin origin=getOperationOriginItem();
		if (origin != null) {
			originCombo.setItem(getOperationOriginItemIndex(origin), origin.getIdentifier());
		}
		addListeners();
	}

	@Override
	protected void addListeners() {
		titleCombo.addModifyListener(listener);
		originCombo.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		titleCombo.removeModifyListener(listener);
		originCombo.removeModifyListener(listener);
	}

	public CCombo getTitleCombo() {
		return titleCombo;
	}

	public CCombo getOriginCombo() {
		return originCombo;
	}

	private OperationTitle getOperationTitle() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getOperationTitle();
	}

	private int getOperationTitleItemIndex(OperationTitle operationTitle) {
		List<OperationTitle> sortedTitles=getSortedTitles();
		return sortedTitles.indexOf(operationTitle);
	}

	private List<OperationTitle> getSortedTitles() {
		Assert.isTrue(currentEObject instanceof Operation);
		Tracker tracker=TrackerUtils.getTracker(currentEObject);
		List<OperationTitle> operationTitles=tracker.getOperationsTitlesRepositories().getOperationsTitles();
		Collections.sort(operationTitles, new OperationTitleComparator());
		return operationTitles;
	}

	private String[] getOperationTitlesItems() {
		List<OperationTitle> operationTitles=getSortedTitles();
		String[] items=new String[operationTitles.size()];
		for (int i=0; i < operationTitles.size(); i++) {
			items[i]=operationTitles.get(i).getTitle();
		}
		return items;
	}

	private Origin getOperationOriginItem() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getOrigin();
	}

	private int getOperationOriginItemIndex(Origin origin) {
		List<Origin> sortedOrigins=getSortedOrgins();
		return sortedOrigins.indexOf(origin);
	}

	private List<Origin> getSortedOrgins() {
		Assert.isTrue(currentEObject instanceof Operation);
		Tracker tracker=TrackerUtils.getTracker(currentEObject);
		List<Origin> origins=tracker.getOriginsRepository().getOrigins();
		Collections.sort(origins, new OriginComparator());
		return origins;
	}

	private String[] getOriginsItems() {
		List<Origin> origins=getSortedOrgins();
		String[] items=new String[origins.size()];
		for (int i=0; i < origins.size(); i++) {
			items[i]=origins.get(i).getIdentifier();
		}
		return items;
	}
}
