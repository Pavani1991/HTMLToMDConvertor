package com.msft.textconvertors.TextConvertors;

import java.util.List;

/**
 * Table Info class that contains header and data information of the table.
 * @author Pavani Gorantla
 */
public class TableInfo {
    private final TableRecord header;
    private final List<TableRecord> data;

    TableInfo(final TableRecord header, final List<TableRecord> data) {
        this.header = header;
        this.data = data;
    }

    /**
     * @return the header.
     */
    public TableRecord getHeader() {
        return header;
    }

    /**
     * @return the data.
     */
    public List<TableRecord> getData() {
        return data;
    }
}
