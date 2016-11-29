/*******************************************************************************
 * Intel(R) CoFluent(TM) Studio - Intel Corporation
 * Copyright (C) 2003-2016, Intel Corporation. All rights reserved.
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
