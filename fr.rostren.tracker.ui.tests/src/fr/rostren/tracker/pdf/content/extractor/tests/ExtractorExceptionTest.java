/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2016, Intel Corporation. All rights reserved.
 *******************************************************************************/
package fr.rostren.tracker.pdf.content.extractor.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.logging.Level;

import org.junit.Test;

import fr.rostren.tracker.pdf.content.extractor.ExtractorException;

public class ExtractorExceptionTest {

	private final String message="message"; //$NON-NLS-1$
	private final String nullMessage=null;
	private final Level level=Level.INFO;
	private final Level nullLevel=null;
	private final Throwable cause=null;
	private ExtractorException exception;

	/**
	 * Tests constructors
	 */
	@Test
	public void constructorTest() {
		exception=new ExtractorException();
		assertNotNull(exception);

		exception=new ExtractorException(nullMessage);
		assertNotNull(exception);
		assertNull(exception.getMessage());
		assertNotNull(exception.getLevel());
		assertEquals(Level.SEVERE, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(nullLevel, nullMessage);
		assertNotNull(exception);
		assertNull(exception.getMessage());
		assertNull(exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(nullLevel, message);
		assertNotNull(exception);
		assertEquals(message, exception.getMessage());
		assertNull(exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(nullMessage, cause);
		assertNotNull(exception);
		assertNull(exception.getMessage());
		assertEquals(Level.SEVERE, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(message);
		assertNotNull(exception);
		assertEquals(message, exception.getMessage());
		assertEquals(Level.SEVERE, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(message, cause);
		assertNotNull(exception);
		assertEquals(message, exception.getMessage());
		assertEquals(Level.SEVERE, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(level, message);
		assertNotNull(exception);
		assertEquals(message, exception.getMessage());
		assertEquals(level, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(level, message, cause);
		assertNotNull(exception);
		assertEquals(message, exception.getMessage());
		assertEquals(level, exception.getLevel());
		assertNull(exception.getCause());

		exception=new ExtractorException(null, null, null);
		assertNotNull(exception);
		assertNull(exception.getMessage());
		assertNull(exception.getLevel());
		assertNull(exception.getCause());
	}

}
