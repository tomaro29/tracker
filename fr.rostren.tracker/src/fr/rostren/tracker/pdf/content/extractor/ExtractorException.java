package fr.rostren.tracker.pdf.content.extractor;

import java.util.logging.Level;

/**
 * The {@link PDFContentExtractor} exception.
 * 
 * @author maro
 *
 */
public class ExtractorException extends Exception {
	/** the serial version. */
	private static final long serialVersionUID = 1L;
	/** the serial version. */
	private static final Level DEFAULT_LEVEL = Level.SEVERE;

	/** the message. */
	private final String message;
	/** the level. */
	private final Level level;
	/** the cause. */
	private final Throwable cause;

	/**
	 * The Constructor.
	 * 
	 * @param message
	 *            the message.
	 * 
	 */
	ExtractorException(String message) {
		this.message = message;
		this.level = DEFAULT_LEVEL;
		this.cause = null;
	}

	/**
	 * The Constructor.
	 * 
	 * @param message
	 *            the message.
	 * @param cause
	 *            the cause.
	 * 
	 */
	ExtractorException(String message, Throwable cause) {
		this.message = message;
		this.level = DEFAULT_LEVEL;
		this.cause = cause;
	}

	/**
	 * The Constructor.
	 * 
	 * @param level
	 *            the level.
	 * @param message
	 *            the message.
	 * 
	 */
	ExtractorException(Level level, String message) {
		this.message = message;
		this.level = level;
		this.cause = null;
	}

	/**
	 * The Constructor.
	 * 
	 * @param level
	 *            the level.
	 * @param message
	 *            the message.
	 * @param cause
	 *            the cause.
	 */
	ExtractorException(Level level, String message, Throwable cause) {
		this.message = message;
		this.level = level;
		this.cause = cause;
	}

	/**
	 * Returns the message.
	 * 
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * Returns the level.
	 * 
	 * @return the level
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Returns the cause.
	 * 
	 * @return the cause
	 */
	@Override
	public Throwable getCause() {
		return this.cause;
	}
}
