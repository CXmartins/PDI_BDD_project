package org.pentaho.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by carlos on 8/4/16.
 */
public class ExecuteTest {
    public static String search_path="tests/spec/";
    public static String script_dir="bin/";
    public static String script_name="run_job.sh";
    public static String kettle_dir;
    public static void main(String[] args) {
        //search_path=args[0];
        //script_dir=args[1];


        Result result = JUnitCore.runClasses(TestETL.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
