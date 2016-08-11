package org.pentaho.tests;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


@RunWith(Parameterized.class)
public class TestETL {
    private String filename;


    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method
    public TestETL(String filename, String name) {
        this.filename=filename;
    }

    @Parameterized.Parameters( name = "{index}: {1}" )
    public static Collection etl_files() {
        FileSearch fileSearch = new FileSearch();
        try{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        //try different directory and filename :)
        fileSearch.searchDirectory(new File(ExecuteTest.search_path), "unit_test.kjb");
        int count = fileSearch.getResult().size();
        ArrayList<String[]> filenames = new ArrayList();
        if (count == 0) {
            System.out.println("\nNo result found!");
            return  null;
        } else {
            for (String matched : fileSearch.getResult()) {


                    File file = new File(matched);
                    Document document = documentBuilder.parse(file);
                    String name = document.getElementsByTagName("name").item(0).getTextContent();
                    filenames.add(new String[]{matched,name});

            }
            return filenames;
        }

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    @Test
    public void testETLRunner() {
        int result = -1;

        String dir = System.getProperty("KETTLE_DIR");
        ProcessBuilder script = new ProcessBuilder(ExecuteTest.script_dir+ExecuteTest.script_name,this.filename, dir);
        try {
            Process p = script.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            p.waitFor();
            String output = br.readLine();
            if(!output.isEmpty())
                result = Integer.parseInt(output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.filename);
        assertEquals(0, result);
    }
}
