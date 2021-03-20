package com.msft.textconvertors.TextConvertors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 * HTMLToMDParser will convert the HTML tables to MD tables, which can be extended later to support all others HTML tags
 * @author Pavani Gorantla
 */
public class HTMLToMDParser{
    public static void main(final String args[]) throws IOException {
        final File file = new File("src/main/resources/resource.html");
        final Document html = Jsoup.parse(file, "UTF-8");
        final String parsedText =  parseDocument(html);
        System.out.println("parsedText:");
        System.out.println(parsedText);
    }

    private static String parseDocument(final Document dirtyDoc) {
        final int indentation = -1;

        final String title = dirtyDoc.title();

        final Whitelist whitelist = Whitelist.relaxed();
        final Cleaner cleaner = new Cleaner(whitelist);

        final Document doc = cleaner.clean(dirtyDoc);
        doc.outputSettings().escapeMode(EscapeMode.xhtml);

        if (!title.trim().equals("")) {
            return "# " + title + "\n\n" + getTables(doc);
        } else {
            return getTables(doc);
        }
    }

    private static String getTables(final Document dirtyDoc) {
        final TableInfo info = new TableInfo();
        final List<Element> elements = dirtyDoc.getElementsByTag("tr");
        int i=0;
        if(elements.get(0).getElementsByTag("th").size() > 0) {
            info.setHeader(createTableRecord(elements.get(0), "th"));
            i++;
        }
        final List<TableRecord> data = new ArrayList<TableRecord>();
        for(;i < elements.size(); i++) {
            data.add(createTableRecord(elements.get(i), "td"));
        }
        info.setData(data);

        return MDTable.convertTableInfoToMdTable(info);
    }

    private static TableRecord createTableRecord(final Element element, final String tag) {
        final List<Element> dataElements = element.getElementsByTag(tag);
        final List<String> data = new ArrayList<String>();
        for(final Element dataElement: dataElements) {
            data.add(dataElement.text());
        }
        return new TableRecord(data);
    }
}