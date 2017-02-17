package fr.rostren.tracker.ui.properties.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractAddWizardPage extends AbstractWizardPage {

	/**
	 * Constructor
	 * @param pageName the page name
	 */
	protected AbstractAddWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container=new Composite(parent, SWT.NONE);
		GridLayout layout=new GridLayout();
		layout.numColumns=2;
		layout.makeColumnsEqualWidth=false;
		layout.marginWidth=0;
		layout.marginHeight=0;
		container.setLayout(layout);

		createContainer(container);

		setControl(container);
		setPageComplete(true);
	}

	/**
	 * Creates a container
	 * @param parent the composite parent of the container to create
	 */
	abstract protected void createContainer(Composite parent);
}
