package com.msft.textconvertors.TextConvertors;

import java.util.List;

/**
 * Helper class to convert the table information to md format
 * @author Pavani Gorantla
 */
public class MDTableUtil {
    /**
     * Convert the {@TableInfo} details to md string
     * @param tableInfo object that needs to be formatted to md string
     * @return the string value that is in md format
     */
    public static String convertTableInfoToMdTable(final TableInfo tableInfo) {
        final TableRecord header = tableInfo.getHeader();
        final List<TableRecord> data = tableInfo.getData();
        if(header ==  null && data.size() == 0) {
            return "";
        }
        int cols = 0;
        if(header != null) {
            cols = header.getRecords().size();
        } else {
            cols = data.get(0).getRecords().size();
        }
        final StringBuilder mdHeaderBuilder = buildHeader(header, cols);
        final StringBuilder mdSeperator = buildSeperator(cols);
        final StringBuilder mdContentBuilder = buildContent(tableInfo.getData());
        final String result = new StringBuilder().append(mdHeaderBuilder).append(mdSeperator).append(mdContentBuilder).append("\n").toString();
        return result;
    }

    private static StringBuilder buildHeader(final TableRecord header, final int cols) {
        final StringBuilder sbHeaderBuilder = new StringBuilder();
        if(header != null) {
            final StringBuilder sb = new StringBuilder();
            sbHeaderBuilder.append(getContentFromTableRecord(header));
        } else {
            final StringBuilder sb = new StringBuilder();
            sbHeaderBuilder.append("|");
            for(int i=0; i< cols; i++) {
                sbHeaderBuilder.append(" ").append("|");
            }
            sbHeaderBuilder.append("\n");
        }
        return sbHeaderBuilder;
    }

    private static StringBuilder buildSeperator(final int cols) {
        final StringBuilder seperatorSb = new StringBuilder();
        seperatorSb.append("|");
        for(int i=0; i< cols; i++) {
            seperatorSb.append(":---").append("|");
        }
        seperatorSb.append("\n");
        return seperatorSb;
    }

    private static StringBuilder buildContent(final List<TableRecord> records) {
        final StringBuilder contentBuilder = new StringBuilder();
        for(final TableRecord record: records) {
            contentBuilder.append(getContentFromTableRecord(record));
        }
        return contentBuilder;
    }

    private static String getContentFromTableRecord(final TableRecord record) {
        final StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("|");
        for(final Record recordInfo: record.getRecords()) {
            contentBuilder.append(retrieveContent(recordInfo)).append("|");
        }
        contentBuilder.append("\n");
        return contentBuilder.toString();
    }

    private static String retrieveContent(final Record record) {
        final StringBuilder contentBuilder = new StringBuilder();
        switch(record.getFormat()) {
        case BOLD_ITALIC:
            contentBuilder.append("*__").append(record.getData()).append("__*");
            break;
        case BOLD:
            contentBuilder.append("__").append(record.getData()).append("__");
            break;
        case ITALIC:
            contentBuilder.append("*").append(record.getData()).append("*");
            break;
        default:
            contentBuilder.append(record.getData());
        }
        return contentBuilder.toString();
    }
}
