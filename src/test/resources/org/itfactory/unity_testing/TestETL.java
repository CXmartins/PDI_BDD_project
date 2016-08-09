package test.resources.org.itfactory.unity_testing; /**
 * Created by carlos on 8/4/16.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestETL {
    private String filename;


    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method
    public TestETL(String filename) {
        this.filename=filename;
    }

    @Parameterized.Parameters( name = "{index}: {0}" )
    public static Collection etl_files() {
        FileSearch fileSearch = new FileSearch();

        //try different directory and filename :)
        fileSearch.searchDirectory(new File(ExecuteTest.search_path), "unit_test.kjb");

        int count = fileSearch.getResult().size();
        ArrayList<String> filenames = new ArrayList();
        if (count == 0) {
            System.out.println("\nNo result found!");
            return  null;
        } else {
            for (String matched : fileSearch.getResult()) {
                filenames.add(matched);
            }
            return filenames;
        }



    }


    @Test
    public void testETLRunner() {
        int result = -1;
        ProcessBuilder script = new ProcessBuilder(ExecuteTest.script_dir+ExecuteTest.script_name,this.filename);
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
