package fr.rostren.tracker.ui.properties.label.providers;

import org.apache.commons.lang.StringUtils;

import fr.rostren.tracker.Origin;

public class OriginLabelProvider extends AbstractLabelProvider {
	protected static final String STRING_UNDEFINED_TYPE="UNDEFINED TYPE"; //$NON-NLS-1$
	protected static final String STRING_UNDEFINED_ID="UNDEFINED ID"; //$NON-NLS-1$

	@Override
	public String getText(Object element) {
		if (element instanceof Origin) {
			return getOriginType((Origin)element)	+ AbstractLabelProvider.STRING_SEPARATOR + AbstractLabelProvider.QUOTES + getOriginIdentifier((Origin)element)
					+ AbstractLabelProvider.QUOTES;
		}
		return super.getText(element);
	}

	/**
	 * The origin type
	 * @param origin the given {@link Origin}
	 * @return the origin type
	 */
	private String getOriginType(Origin origin) {
		return origin.getType() == null ? OriginLabelProvider.STRING_UNDEFINED_TYPE : origin.getType().getLiteral();
	}

	/**
	 * The origin identifier
	 * @param origin the given {@link Origin}
	 * @return the origin identifier
	 */
	private String getOriginIdentifier(Origin origin) {
		return StringUtils.isEmpty(origin.getIdentifier()) ? OriginLabelProvider.STRING_UNDEFINED_ID : origin.getIdentifier();
	}
}
