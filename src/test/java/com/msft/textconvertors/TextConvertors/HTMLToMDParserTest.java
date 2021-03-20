package com.msft.textconvertors.TextConvertors;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple HTMLToMDParser.
 * @author Pavani Gorantla
 */
public class HTMLToMDParserTest extends TestCase {

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( HTMLToMDParserTest.class );
    }

    /**
     * Test tables with header and data
     */
    public void testTablesWithHeader()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithHeader.html";
            }

            @Override
            protected void assertData() throws IOException {
                final File file1 = new File(filePath+"/validateTablesWithHeader.md");
                final File file2 = new File(filePath+"/tablesWithHeader.md");
                Assert.assertTrue(FileUtils.contentEquals(file1, file2));
                file2.delete();
            }
        }.performTest();
    }

    /**
     * Test tables with out header
     */
    public void testTablesWithOutHeader()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithOutHeader.html";
            }

            @Override
            protected void assertData() throws IOException {
                final File file1 = new File(filePath+"/validateTablesWithOutHeader.md");
                final File file2 = new File(filePath+"/tablesWithOutHeader.md");
                Assert.assertTrue(FileUtils.contentEquals(file1, file2));
                file2.delete();
            }
        }.performTest();
    }

    /**
     * Test tables with bold text
     */
    public void testTablesWithBoldText()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithBoldText.html";
            }

            @Override
            protected void assertData() throws IOException {
                final File file1 = new File(filePath+"/validateTablesWithBoldText.md");
                final File file2 = new File(filePath+"/tablesWithBoldText.md");
                Assert.assertTrue(FileUtils.contentEquals(file1, file2));
                file2.delete();
            }
        }.performTest();
    }

    /**
     * Test tables with bold and italic text
     */
    public void testTablesWithBoldAndItalictext()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithBoldAndItalicText.html";
            }

            @Override
            protected void assertData() throws IOException {
                final File file1 = new File(filePath+"/validateTablesWithBoldAndItalicText.md");
                final File file2 = new File(filePath+"/tablesWithBoldAndItalicText.md");
                Assert.assertTrue(FileUtils.contentEquals(file1, file2));
                file2.delete();
            }
        }.performTest();
    }

    /**
     * Test tables with italic text
     */
    public void testTablesWithItalictext()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithItalicText.html";
            }

            @Override
            protected void assertData() throws IOException {
                final File file1 = new File(filePath+"/validateTablesWithItalicText.md");
                final File file2 = new File(filePath+"/tablesWithItalicText.md");
                Assert.assertTrue(FileUtils.contentEquals(file1, file2));
                file2.delete();
            }
        }.performTest();
    }


    /**
     * Test tables with invalid file name
     */
    public void testTablesWithInvalidFileName()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "tablesWithItalicText.txt";
            }

            @Override
            protected void assertData() throws IOException {
            }

            @Override
            protected void assertException(final Exception e) {
                Assert.assertTrue(e instanceof IllegalArgumentException);
                Assert.assertTrue(e.getMessage().contains("Provided file should be html file"));
            }
        }.performTest();
    }

    /**
     * Test tables with non existing file
     */
    public void testTablesWithNonExistingFile()
    {
        new TestPlan() {

            @Override
            protected void setFileName() {
                fileName = "NOTable.html";
            }

            @Override
            protected void assertData() throws IOException {
            }

            @Override
            protected void assertException(final Exception e) {
                Assert.assertTrue(e instanceof IOException);
            }
        }.performTest();
    }

    /**
     * Test tables with non existing file
     */
    public void testTablesWithInvalidArguments()
    {
        try {
            final String[] arguments = new String[3];
            arguments[0] = "arg1";
            arguments[1] = "arg2";
            arguments[2] = "arg3";

            final HTMLToMDParser obj = new HTMLToMDParser();
            obj.main(arguments);
        } catch(final Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
            Assert.assertTrue(e.getMessage().contains("Invalid number of arguments are provided"));
        }
    }

    protected abstract class TestPlan {
        String fileName;
        String filePath = "src/test/resources";
        public void performTest() {
            try {
                setFileName();
                final String[] arguments = new String[2];
                arguments[0] = filePath;
                arguments[1] = fileName;

                final HTMLToMDParser obj = new HTMLToMDParser();
                obj.main(arguments);
                assertData();
            } catch (final Exception e) {
                assertException(e);
            }
        }

        protected abstract void setFileName();

        protected abstract void assertData() throws IOException;

        protected void assertException(final Exception e) {

        }
    }
}
