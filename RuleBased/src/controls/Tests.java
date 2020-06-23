package controls;

import logic.FilesUtils;

import java.util.ArrayList;

public class Tests {

    public static void updateTests(String testTemplate, String newTestResults) {

        if (!FilesUtils.replaceInFile(testTemplate, newTestResults)) {
        	FilesUtils.writeSingleProlog(newTestResults);
        }
    }
}
