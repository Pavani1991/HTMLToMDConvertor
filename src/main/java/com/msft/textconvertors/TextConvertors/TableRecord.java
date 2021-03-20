package com.msft.textconvertors.TextConvertors;

import java.util.List;

/**
 * Table record represent the entire row of the data.
 * @author Pavani Gorantla
 */
public class TableRecord {
    private final List<Record> records;
    /**
     * @return the records.
     */
    public List<Record> getRecords() {
        return records;
    }
    /**
     *
     */
    public TableRecord(final List<Record> records) {
        this.records = records;
    }

}
