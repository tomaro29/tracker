/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.ui.views;

import org.eclipse.jface.dialogs.IMessageProvider;

public class TrackerViewMessage {

	private final String location;
	private final String message;
	private final int level;
	private final Throwable exception;

	/**
	 * Constructor.
	 * @param location the concerned repository location
	 * @param message the message content
	 * @param level the {@link IMessageProvider} constant for regular message
	 * @param exception the throwable
	 * the current entry in preferences page, {@code false} otherwise.
	 */
	public TrackerViewMessage(String location, String message, int level, Throwable exception) {
		this.location=location;
		this.message=message;
		this.level=level;
		this.exception=exception;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the exception
	 */
	public Throwable getException() {
		return exception;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime * result + level;
		result=prime * result + (location == null ? 0 : location.hashCode());
		result=prime * result + (message == null ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		TrackerViewMessage other=(TrackerViewMessage)obj;
		return levelEquals(other.level) && locationEquals(other.location) && messageEquals(other.message);
	}

	/**
	 * <code>true</code> if the level is equal to the argument, <code>false</code> otherwise.
	 * @param objLevel the level
	 * @return <code>true</code> if the level is equal to the argument, <code>false</code> otherwise.
	 */
	private boolean levelEquals(int objLevel) {
		return level == objLevel;
	}

	/**
	 * <code>true</code> if the location is equal to the argument, <code>false</code> otherwise.
	 * @param objLocation the location
	 * @return <code>true</code> if the location is equal to the argument, <code>false</code> otherwise.
	 */
	private boolean locationEquals(String objLocation) {
		boolean notEquals=location == null && objLocation != null;
		notEquals=notEquals || location != null && !location.equals(objLocation);
		return !notEquals;
	}

	/**
	 * <code>true</code> if the message is equal to the argument, <code>false</code> otherwise.
	 * @param objMessage the message
	 * @return <code>true</code> if the message is equal to the argument, <code>false</code> otherwise.
	 */
	private boolean messageEquals(String objMessage) {
		boolean notEquals=message == null && objMessage != null;
		notEquals=notEquals || message != null && !message.equals(objMessage);
		return !notEquals;
	}
}
