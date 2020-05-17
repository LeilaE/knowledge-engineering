package controls;

import logic.FilesUtils;

import java.util.ArrayList;

public class Tests {

    public static void updateTests(String testTemplate, ArrayList<String> newTestResults) {

        for (String test : newTestResults) {
            FilesUtils.replaceInFile(testTemplate, test);
        }
    }
}
