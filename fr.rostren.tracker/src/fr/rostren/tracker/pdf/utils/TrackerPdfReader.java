/*******************************************************************************
 * Copyright (c) 2017
 * All rights reserved.
 *
 * Contributors:
 *     <mrostren> Initial code
 *******************************************************************************/
package fr.rostren.tracker.pdf.utils;

import java.io.Closeable;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

public class TrackerPdfReader extends PdfReader implements Closeable {
	/**
	 * Constructor
	 * @param src the pdf uri to read
	 * @throws IOException if an {@link IOException} has ocurred
	 */
	public TrackerPdfReader(String src) throws IOException {
		super(src);
	}

	/* (non-Javadoc)
	 * @see com.itextpdf.text.pdf.PdfReader#close()
	 */
	@Override
	public void close() {
		super.close();
	}
}
