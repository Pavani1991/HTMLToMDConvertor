package com.msft.textconvertors.TextConvertors;

/**
 * Record contains the data and format in which the data is available in the HTML file.
 * @author Pavani Gorantla
 */
public class Record {
    private final String data;
    private final FontFormat format;
    Record(final String data) {
        this.data = data;
        this.format = FontFormat.DEFAULT;
    }

    Record(final String data, final FontFormat format) {
        this.data = data;
        this.format = format;
    }

    /**
     * @return the data.
     */
    public String getData() {
        return data;
    }

    /**
     * @return the format.
     */
    public FontFormat getFormat() {
        return format;
    }

}
