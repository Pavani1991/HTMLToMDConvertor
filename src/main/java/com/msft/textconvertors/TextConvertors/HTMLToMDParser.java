package com.msft.textconvertors.TextConvertors;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/**
 * HTMLToMDParser will convert the HTML tables to MD tables.
 * @author Pavani Gorantla
 */
public class HTMLToMDParser{
    /**
     *
     * Currently main is handling the entire workflow. The html file information is retrieved from args.
     * @param args retrieves the information about the html file. args[0] contains path to the file. args[1] should contain file name.
     * @throws IllegalArgumentException if the number of arguments is less than or greater than two and if the filename does not include html extension.
     * @throws IOException if the file retrieval is failed.
     */
    public static void main(final String args[]) throws IOException, IllegalArgumentException {
        if(args.length < 2 || args.length > 2) {
            throw new IllegalArgumentException("Invalid number of arguments are provided");
        };
        StringJoiner joiner = new StringJoiner("/");
        final String resourcePath = args[0];
        final String fileName = args[1];
        final String currentExtension = fileName.substring(fileName.lastIndexOf(".")+1);
        if(!"html".equals(currentExtension)) {
            throw new IllegalArgumentException("Provided file should be html file");
        }
        joiner.add(resourcePath).add(fileName);
        final String filePath = joiner.toString();
        final File file = new File(filePath);

        System.out.println("parsing the file");
        final Document html = Jsoup.parse(file, "UTF-8");
        final String parsedText =  parseDocument(html);
        System.out.println("parsed text:");
        System.out.println(parsedText);

        final String newFileExtension = "md";
        final String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
        final String newFileName = new StringBuilder().append(fileNameWithoutExtension).append(".").append(newFileExtension).toString();
        joiner = new StringJoiner("/");
        joiner.add(resourcePath).add(newFileName);
        final String responseFilePath = joiner.toString();
        final PrintWriter writer = new PrintWriter(responseFilePath);
        writer.flush();
        writer.write(parsedText);
        writer.close();
        System.out.println("file has been written to:"+responseFilePath);
    }

    private static String parseDocument(final Document dirtyDoc) {
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

    private static String getTables(final Document doc) {
        if(doc.getElementsByTag("table").size() == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for(final Element tableElement: doc.getElementsByTag("table")) {
            TableRecord header = null;
            final List<Element> elements = tableElement.getElementsByTag("tr");
            int i=0;
            if(elements.get(0).getElementsByTag("th").size() > 0) {
                header = createTableRecord(elements.get(0), "th");
                i++;
            }
            final List<TableRecord> data = new ArrayList<TableRecord>();
            for(;i < elements.size(); i++) {
                data.add(createTableRecord(elements.get(i), "td"));
            }
            final TableInfo info = new TableInfo(header, data);
            sb.append(MDTableUtil.convertTableInfoToMdTable(info));
        }

        return sb.toString();
    }

    private static TableRecord createTableRecord(final Element element, final String tag) {
        final List<Element> dataElements = element.getElementsByTag(tag);
        final List<Record> records = new ArrayList<Record>();
        for(final Element dataElement: dataElements) {
            boolean isBold = false;
            boolean isItalic = false;
            FontFormat format = FontFormat.DEFAULT;
            if(dataElement.getElementsByTag("b").size() > 0) {
                isBold = true;
            }
            if(dataElement.getElementsByTag("i").size() > 0) {
                isItalic = true;
            }
            if(isBold && isItalic) {
                format = FontFormat.BOLD_ITALIC;
            } else if(isBold) {
                format = FontFormat.BOLD;
            } else if(isItalic) {
                format = FontFormat.ITALIC;
            }
            records.add(new Record(dataElement.text(), format));
        }
        return new TableRecord(records);
    }
}