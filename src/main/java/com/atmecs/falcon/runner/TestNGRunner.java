package com.atmecs.falcon.runner;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.atmecs.falcon.automation.appium.manager.AppiumParallelTest;
import com.atmecs.falcon.automation.appium.manager.Runner;
import com.atmecs.falcon.automation.run.mode.RunModeFactory;
import com.atmecs.falcon.automation.util.logging.LogLevel;
import com.atmecs.falcon.automation.util.logging.LogManager;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.util.reporter.TestReportUploadClient;
import com.atmecs.falcon.automation.utils.enums.ERunModeType;
import com.atmecs.falcon.automation.utils.general.PropertyReader;

/**
 * @author Vibha.Rani
 */
public class TestNGRunner {

    static Properties prop = new Properties();
    private static ReportLogService report = new ReportLogServiceImpl(TestNGRunner.class);
    private static boolean isUserProvideTestng = false;

    /**
     * String Constants
     **/
    static final String SUITE_FILES = "SUITE_FILES";
    static final String MODULE_NAMES = "MODULE_NAMES";

    public static String getModuleNameForPackage(String p) {
        String[] mArr = null;
        if (System.getProperties().getProperty("os.name").contains("Windows")) {
            mArr = p.split(File.separator + File.separator);
        } else if (System.getProperties().getProperty("os.name").contains("Mac")) {
            mArr = p.split(File.separator);
        }
        String moduleName = mArr[mArr.length - 1];
        return moduleName;
    }

    private static ERunModeType getRunModeType() {
        String runMode = PropertyReader.readEnvOrConfigProperty("RUN_MODE");
        if (runMode.equals("LOCAL")) {
            return ERunModeType.LOCAL_RUN;
        } else if (runMode.equals("REMOTE")) {
            return ERunModeType.REMOTE_RUN;
        } else if (runMode.equals("CLOUD")) {
            return ERunModeType.CLOUD_RUN;
        } else {
            return ERunModeType.LOCAL_RUN;
        }
    }

    /**
     * @author atmecs
     * @Description: reads the values from properties file i.e. user is provided the testng.xml or
     *               provided the packages If the packages are provided checks the packages are
     *               provided in the project If the packages are available pass the package names
     *               separated by "," to testApp function for further process
     **/
    public static void main(String[] args) throws Exception {
        try {
            AppiumParallelTest.runMode = RunModeFactory.getRunMode(getRunModeType());
            LogManager.setLogLevel(LogLevel.INFO);

            String packageName = "";
            String userProvidedTestNGFiles = PropertyReader.readEnvOrConfigProperty(SUITE_FILES);

            if (!(userProvidedTestNGFiles.trim().isEmpty() || userProvidedTestNGFiles == null)) {
                isUserProvideTestng = true;
            }

            String userProvidedModuleList = PropertyReader.readEnvOrConfigProperty(MODULE_NAMES);
            if (userProvidedModuleList.trim().isEmpty() || userProvidedModuleList == null
                    || isUserProvideTestng) {
                if (isUserProvideTestng) {
                    System.out.println("User provided its own testNG");
                } else {
                    System.out
                            .println("UserProvided Module list is Null : Adding all packages Containig Tests");
                }
            } else {
                String path =
                        System.getProperty("user.dir") + File.separator + "src" + File.separator
                                + "main" + File.separator + "java" + File.separator;

                File file = new File(path);

                TestScriptPackage obj = new TestScriptPackage();
                obj.getPackage(file);
                String x = obj.getPack().replaceFirst(",", "");
                String[] totalPackages = x.split(",");

                String[] userProvidedModules = userProvidedModuleList.split(",");
                int count = 0;
                if (userProvidedModules.length >= 1) {
                    for (String userProvidedModule : userProvidedModules) {
                        for (String p : totalPackages) {
                            System.out.println(userProvidedModule + "==================="
                                    + getModuleNameForPackage(p));
                            if (userProvidedModule.equals(getModuleNameForPackage(p))) {
                                packageName += "," + p;
                            } else {
                                count++;
                            }
                            if (count == totalPackages.length) {
                                System.out.println("package is not available!!!!!!!!!!!!");
                                // add exception
                                // if the user provide invalid package name (module name)
                                throw new Exception("The provided package is invalid");
                            }
                        }
                        count = 0;
                    }
                    packageName = packageName.replaceFirst(",", "");
                } else {
                    packageName = x;
                }
            }

            System.out.println("Printing Package name " + packageName);
            Runner.testApp(packageName.replaceFirst(",", ""));
          //  uploadTestNGResultsXml();

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author
     * @exception Error in Network connection
     * @Description: upload the generated testng-results.xml to the report server
     **/
    public static void uploadTestNGResultsXml() {
        try {
            prop.load(new FileInputStream("config.properties"));
            String uploadUrl = prop.getProperty("testreport.uploadurl");
            String testNGResultsXmlFilePath =
                    System.getProperty("user.dir") + File.separator + "test-output"
                            + File.separator + "testng-results.xml";
            TestReportUploadClient testReportUploadClient = new TestReportUploadClient(uploadUrl);
            report.info("Started Uploading Results to Report Server...");

            String response =
                    testReportUploadClient.upload("1000", "FALCON", "Mobile", "QA", "Regression",
                            "Local", "", "Android", "", testNGResultsXmlFilePath);
            report.info("Response : " + response);

        } catch (Exception e) {
            report.info("Unknown error : Cannot Upload the testng-results.xml " + e.getMessage());
        }
    }
}
