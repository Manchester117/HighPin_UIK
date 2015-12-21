package com.highpin.operatordata;

import com.highpin.tools.Utility;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2015/12/21.
 */
public class ReadAllTestCaseFile {
    private File testFolder = null;
    private SortedMap<String, SortedMap<String, SortedMap<String, Map<String, Object>>>> allTestDataMap = null;

    public ReadAllTestCaseFile() {
        this.testFolder = new File("cases");
        this.allTestDataMap = new TreeMap<>();
    }

    public SortedMap<String, SortedMap<String, SortedMap<String, Map<String, Object>>>> readTestSuite() throws Exception {
        String testSuiteName = null;
        File [] testSuiteList = this.testFolder.listFiles();
        SortedMap<String, SortedMap<String, Map<String, Object>>> testMap = null;

        if (testSuiteList != null && testSuiteList.length > 0) {
            for (File file : testSuiteList) {
                testSuiteName = file.getName();
                if (testSuiteName.startsWith("test_")) {
                    // 实例化Excel数据读取
                    ExcelOperator eo = new ExcelOperator("cases/" + testSuiteName);
                    // 获取单个Excel数据结构
                    testMap = eo.traverseTestSteps();
                    // 获取文件名(不带后缀)
                    testSuiteName = testSuiteName.substring(0, testSuiteName.indexOf("."));
                    // 将每个Excel生成的数据结构放置在SortedMap当中
                    this.allTestDataMap.put(testSuiteName, testMap);
                    System.out.println(Utility.dataStructConvertJSON(testMap));
                }
            }
            System.out.println(Utility.dataStructConvertJSON(this.allTestDataMap));
        }
        return this.allTestDataMap;
    }

    public static void main(String[] args) throws Exception {
        ReadAllTestCaseFile rf = new ReadAllTestCaseFile();
        rf.readTestSuite();
    }
}