package fr.rostren.tracker.pdf.content.extractor;

import java.util.logging.Level;

public class ExtractorException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Level DEFAULT_LEVEL = Level.SEVERE;
	private String message;
	private Level level;
	private Throwable cause = null;

	public ExtractorException() {
		setMessage(""); //$NON-NLS-1$
		setLevel(DEFAULT_LEVEL);
	}

	ExtractorException(String message) {
		setMessage(message);
		setLevel(DEFAULT_LEVEL);
	}

	ExtractorException(String message, Throwable cause) {
		setMessage(message);
		setLevel(DEFAULT_LEVEL);
		setCause(cause);
	}

	ExtractorException(Level level, String message) {
		setMessage(message);
		setLevel(level);
	}

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
		return this.message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * @return the cause
	 */
	@Override
	public Throwable getCause() {
		return this.cause;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
