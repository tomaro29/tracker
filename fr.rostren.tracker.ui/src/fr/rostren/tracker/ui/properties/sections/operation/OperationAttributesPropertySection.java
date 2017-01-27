package fr.rostren.tracker.ui.properties.sections.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.collect.Sets;

import fr.rostren.tracker.Date;
import fr.rostren.tracker.Operation;
import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.OperationsTitleRepository;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.OriginsRepository;
import fr.rostren.tracker.Tracker;
import fr.rostren.tracker.pdf.utils.TrackerUtils;
import fr.rostren.tracker.ui.properties.content.comparators.OperationTitleComparator;
import fr.rostren.tracker.ui.properties.content.comparators.OriginComparator;
import fr.rostren.tracker.ui.properties.listeners.DateSelectionListener;
import fr.rostren.tracker.ui.properties.listeners.OperationAttributesModifyListener;
import fr.rostren.tracker.ui.properties.sections.AbstractAttributesPropertySection;

public class OperationAttributesPropertySection extends AbstractAttributesPropertySection {
	protected CCombo titleCombo;
	protected DateTime dateTime;
	protected CCombo originCombo;
	protected Text totalAmount;

	private final ModifyListener listener=new OperationAttributesModifyListener(this);
	private final SelectionListener datelistener=new DateSelectionListener(this, dateTime);

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		titleCombo=createLabeledCombo(body, null, "Title:"); //$NON-NLS-1$
		dateTime=createDateTime(body, titleCombo, "Date:"); //$NON-NLS-1$
		originCombo=createLabeledCombo(body, dateTime, "Origin:"); //$NON-NLS-1$
		totalAmount=createLabeledText(body, originCombo, "Total Amount:"); //$NON-NLS-1$
		totalAmount.setEnabled(false);
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
			titleCombo.select(Arrays.asList(items).indexOf(title));
		}
		dateTime.setDate(getYear(), getMonth(), getDay());
		items=getOriginsItems();
		originCombo.setItems(items);
		Origin origin=getOperationOriginItem();
		if (origin != null) {
			String identifier=origin.getIdentifier();
			originCombo.select(Arrays.asList(items).indexOf(identifier));
		}
		totalAmount.setText(getOperationTotalAmount().toString());
		addListeners();
	}

	@Override
	protected void addListeners() {
		titleCombo.addModifyListener(listener);
		dateTime.addSelectionListener(datelistener);
		originCombo.addModifyListener(listener);
	}

	@Override
	protected void disposeListeners() {
		titleCombo.removeModifyListener(listener);
		dateTime.removeSelectionListener(datelistener);
		originCombo.removeModifyListener(listener);
	}

	/**
	 * Returns the title {@link CCombo}
	 * @return the title {@link CCombo}
	 */
	public CCombo getTitleCombo() {
		return titleCombo;
	}

	/**
	 * Returns the Operation wished date year
	 * @return the Operation wished date year
	 */
	private int getYear() {
		Assert.isTrue(currentEObject instanceof Operation);
		Date date=((Operation)currentEObject).getDate();
		return date == null ? 0 : date.getYear();
	}

	/**
	 * Returns the Operation wished date month
	 * @return the Operation wished date month
	 */
	private int getMonth() {
		Assert.isTrue(currentEObject instanceof Operation);
		Date date=((Operation)currentEObject).getDate();
		return date == null ? 0 : date.getMonth().getValue();
	}

	/**
	 * Returns the Operation wished date day
	 * @return the Operation wished date day
	 */
	private int getDay() {
		Assert.isTrue(currentEObject instanceof Operation);
		Date date=((Operation)currentEObject).getDate();
		return date == null ? 0 : date.getDay();
	}

	/**
	 * Returns the origin {@link CCombo}
	 * @return the origin {@link CCombo}
	 */
	public CCombo getOriginCombo() {
		return originCombo;
	}

	/**
	 * Returns the OperationTitle
	 * @return the OperationTitle
	 */
	private OperationTitle getOperationTitle() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getOperationTitle();
	}

	/**
	 * Returns the operation total amount
	 * @return the operation total amount
	 */
	private BigDecimal getOperationTotalAmount() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getTotalAmount();
	}

	/**
	 * Returns the sorted operations titles
	 * @return the sorted operations titles
	 */
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

	/**
	 * Returns the operations titles as an array
	 * @return the operations titles  as an array
	 */
	private String[] getOperationTitlesItems() {
		List<OperationTitle> operationTitles=getSortedTitles();
		List<String> titles=new ArrayList<>();
		for (OperationTitle operationTitle: operationTitles) {
			titles.add(operationTitle.getTitle());
		}
		titles.removeAll(Collections.singleton(null));
		return titles.stream().toArray(String[]::new);
	}

	/**
	 * Returns the operation origin
	 * @return the operation origin
	 */
	private Origin getOperationOriginItem() {
		Assert.isTrue(currentEObject instanceof Operation);
		return ((Operation)currentEObject).getOrigin();
	}

	/**
	 * Returns the sorted origins
	 * @return the sorted origins
	 */
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

	/**
	 * Returns the origins items as an array
	 * @return the origins items as an array
	 */
	private String[] getOriginsItems() {
		List<Origin> origins=getSortedOrgins();
		String[] items=new String[origins.size()];
		for (int i=0; i < origins.size(); i++) {
			items[i]=origins.get(i).getIdentifier();
		}
		return items;
	}
}
