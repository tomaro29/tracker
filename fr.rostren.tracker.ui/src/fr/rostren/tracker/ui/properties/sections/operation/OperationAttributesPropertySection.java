package fr.rostren.tracker.ui.properties.sections.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Sets;

import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
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
		String[] items=getOperationTitlesItems();
		titleCombo.setItems(items);
		if (operationTitle != null) {
			String title=operationTitle.getTitle();
			titleCombo.setItem(Arrays.asList(items).indexOf(title), title);
		}
		items=getOriginsItems();
		originCombo.setItems(items);
		Origin origin=getOperationOriginItem();
		if (origin != null) {
			String identifier=origin.getIdentifier();
			originCombo.setItem(Arrays.asList(items).indexOf(identifier), identifier);
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

	private List<OperationTitle> getSortedTitles() {
		Assert.isTrue(currentEObject instanceof Operation);
		Tracker tracker=TrackerUtils.getTracker(currentEObject);
		OperationsTitleRepository repository=tracker.getOperationsTitlesRepositories();
		if (repository == null) {
			return new ArrayList<>();
		}
		Set<OperationTitle> operationTitles=Sets.newHashSet(repository.getOperationsTitles());
		return getSortedList(operationTitles, new OperationTitleComparator());
	}

	private String[] getOperationTitlesItems() {
		List<OperationTitle> operationTitles=getSortedTitles();
		List<String> titles=new ArrayList<>();
		for (OperationTitle operationTitle: operationTitles) {
			titles.add(operationTitle.getTitle());
		}
		titles.removeAll(Collections.singleton(null));
		return titles.toArray(new String[0]);
	}

	private Origin getOperationOriginItem() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getOrigin();
	}

	private List<Origin> getSortedOrgins() {
		Assert.isTrue(currentEObject instanceof Operation);
		Tracker tracker=TrackerUtils.getTracker(currentEObject);

		OriginsRepository repository=tracker.getOriginsRepository();
		if (repository == null) {
			return new ArrayList<>();
		}

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
