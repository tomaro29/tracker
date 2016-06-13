package fr.rostren.tracker.ui.properties.sections.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.rostren.tracker.OperationTitle;
import fr.rostren.tracker.Origin;
import fr.rostren.tracker.Tracker;

public abstract class AbstractAddWizardPage extends WizardPage {

    protected AbstractAddWizardPage(String pageName) {
	super(pageName);
    }

    @Override
    public void createControl(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	GridLayout layout = new GridLayout();
	container.setLayout(layout);

	createContainer(container);

	setControl(container);
	setPageComplete(true);
    }

    abstract protected void createContainer(Composite parent);

    protected String[] getOperationsTitles(Tracker tracker) {
	List<OperationTitle> titles = new ArrayList<>();
	for (OperationTitle title : tracker.getOperationsTitlesRepositories().getOperationsTitles()) {
	    if (!StringUtils.isEmpty(title.getTitle()))
		titles.add(title);
	}
	String[] titlesAsString = new String[titles.size()];
	for (OperationTitle title : titles) {
	    titlesAsString[titles.indexOf(title)] = title.getTitle();
	}
	return titlesAsString;
    }

    protected String[] getOrigins(Tracker tracker) {
	List<Origin> origins = new ArrayList<>();
	for (Origin origin : tracker.getOriginsRepository().getOrigins()) {
	    if (!StringUtils.isEmpty(origin.getIdentifier()))
		origins.add(origin);
	}
	String[] originsAsString = new String[origins.size()];
	for (Origin origin : origins) {
	    originsAsString[origins.indexOf(origin)] = origin.getIdentifier();
	}
	return originsAsString;
    }

    protected OperationTitle getOperationTitle(Tracker tracker, String text) {
	for (OperationTitle title : tracker.getOperationsTitlesRepositories().getOperationsTitles()) {
	    if (!StringUtils.isEmpty(title.getTitle()) && title.getTitle().equals(text))
		return title;
	}
	return null;
    }

    protected Origin getOrigin(Tracker tracker, String text) {
	for (Origin origin : tracker.getOriginsRepository().getOrigins()) {
	    if (!StringUtils.isEmpty(origin.getIdentifier()) && origin.getIdentifier().equals(text))
		return origin;
	}
	return null;
    }

    protected Combo createCombo(Composite composite, String label, String[] items, ModifyListener modifyListener) {
	Label textLabel = new Label(composite, SWT.NONE);
	textLabel.setText(label);

	Combo combo = new Combo(composite, SWT.NONE);
	combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	combo.setItems(items);
	combo.select(0);

	combo.addModifyListener(modifyListener);
	return combo;
    }

}
