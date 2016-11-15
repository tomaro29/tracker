package fr.rostren.tracker.pdf.content.extractor;

import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;

public class ExtractorException extends Exception {
	private static final long serialVersionUID=1L;
	private static final Level DEFAULT_LEVEL=Level.SEVERE;
	private String message;
	private Level level;
	private Throwable cause=null;

	/**
	 * Constructor
	 */
	public ExtractorException() {
		setMessage(StringUtils.EMPTY);
		setLevel(ExtractorException.DEFAULT_LEVEL);
	}

	/**
	 * Constructor
	 * @param message the message
	 */
	ExtractorException(String message) {
		setMessage(message);
		setLevel(ExtractorException.DEFAULT_LEVEL);
	}

	/**
	 * Constructor
	 * @param message the message
	 * @param cause the cause
	 */
	ExtractorException(String message, Throwable cause) {
		setMessage(message);
		setLevel(ExtractorException.DEFAULT_LEVEL);
		setCause(cause);
	}

	/**
	 * Constructor
	 * @param level the level
	 * @param message the message
	 */
	ExtractorException(Level level, String message) {
		setMessage(message);
		setLevel(level);
	}

	/**
	 * Constructor
	 * @param level the level
	 * @param message the message
	 * @param cause the cause
	 */
	ExtractorException(Level level, String message, Throwable cause) {
		setMessage(message);
		setLevel(level);
		setCause(cause);
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message=message;
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Level level) {
		this.level=level;
	}

	/**
	 * @return the cause
	 */
	@Override
	public Throwable getCause() {
		return cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause=cause;
	}
}
