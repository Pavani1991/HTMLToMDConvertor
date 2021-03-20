package com.msft.textconvertors.TextConvertors;

import java.util.List;

/**
 * Description.
 * @author Your Name
 */
public class MDTable {
    public static String convertTableInfoToMdTable(final TableInfo tableInfo) {
        final TableRecord header = tableInfo.getHeader();
        final List<TableRecord> data = tableInfo.getData();
        if(header ==  null && data.size() == 0) {
            return "";
        }
        final StringBuilder sbHeaderBuilder = new StringBuilder();
        int cols = 0;
        if(header != null) {
            cols = header.records.size();
        } else {
            cols = data.get(0).records.size();
        }
        if(header != null) {
            sbHeaderBuilder.append("|").append(String.join("|", header.records)).append("|").toString();
        } else {
            final StringBuilder sb = new StringBuilder();
            sbHeaderBuilder.append("|");
            for(int i=0; i< cols; i++) {
                sbHeaderBuilder.append(" ").append("|");
            }
        }
        sbHeaderBuilder.append("\n");
        final StringBuilder seperatorSb = new StringBuilder();
        seperatorSb.append("|");
        for(int i=0; i< cols; i++) {
            seperatorSb.append(":---").append("|");
        }
        seperatorSb.append("\n");
        final StringBuilder contentBuilder = new StringBuilder();

        for(final TableRecord record: tableInfo.data) {
            contentBuilder.append("|");
            for(final String info: record.records) {
                contentBuilder.append(info).append("|");
            }
            contentBuilder.append("\n");
        }
        final String result = new StringBuilder().append(sbHeaderBuilder).append(seperatorSb).append(contentBuilder).toString();
        //        System.out.println("result:");
        //        System.out.println(result);
        return result;
    }
}
