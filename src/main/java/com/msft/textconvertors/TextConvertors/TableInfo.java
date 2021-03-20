package com.msft.textconvertors.TextConvertors;

import java.util.List;

/**
 * Description.
 * @author Your Name
 */
public class TableInfo {
    int rows;
    int cols;
    TableRecord header;
    List<TableRecord> data;
    /**
     * @return the rows.
     */
    public int getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set.
     */
    public void setRows(final int rows) {
        this.rows = rows;
    }
    /**
     * @return the cols.
     */
    public int getCols() {
        return cols;
    }
    /**
     * @param cols the cols to set.
     */
    public void setCols(final int cols) {
        this.cols = cols;
    }
    /**
     * @return the header.
     */
    public TableRecord getHeader() {
        return header;
    }
    /**
     * @param header the header to set.
     */
    public void setHeader(final TableRecord header) {
        this.header = header;
    }
    /**
     * @return the data.
     */
    public List<TableRecord> getData() {
        return data;
    }
    /**
     * @param data the data to set.
     */
    public void setData(final List<TableRecord> data) {
        this.data = data;
    }
}
