package com.caster.generator.core;

import com.caster.except.NotFoundLocatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Peng.Zhao on 2015/12/15.
 */
public class LocatorTemplate {
    public static Logger logger = LogManager.getLogger(LocatorTemplate.class.getName());
    /**
     * @Description: 根据定位类型和定位值返回对应的元素选择语句
     * @param locatorType -- 元素定位类型
     * @param locatorValue -- 元素定位值
     * @return by -- 返回对应的定位语句
     * @throws Exception -- 如果出现定位类型/定位值不正确则抛出NotFoundLocatorException
     */
    public static String chooseLocator(String locatorType, String locatorValue) throws Exception {
        String by = null;
        switch (locatorType) {
            case "id":
                by = "org.openqa.selenium.By.id(\"" + locatorValue + "\")";
                break;
            case "name":
                by = "org.openqa.selenium.By.name(\"" + locatorValue + "\")";
                break;
            case "xpath":
                by = "org.openqa.selenium.By.xpath(\"" + locatorValue + "\")";
                break;
            case "linkText":
                by = "org.openqa.selenium.By.linkText(\"" + locatorValue + "\")";
                break;
            case "partialLinkText":
                by = "org.openqa.selenium.By.partialLinkText(\"" + locatorValue + "\")";
                break;
            case "tagName":
                by = "org.openqa.selenium.By.tagName(\"" + locatorValue + "\")";
                break;
            case "className":
                by = "org.openqa.selenium.By.className(\"" + locatorValue + "\")";
                break;
            case "cssSelector":
                by = "org.openqa.selenium.By.cssSelector(\"" + locatorValue + "\")";
                break;
            case "javaScript":
                by = locatorValue;
                break;
            case "ie":
                by = "java.lang.Runtime.getRuntime().exec(\"execute/UpLoadFile.exe 选择要加载的文件 " + locatorValue + "\").waitFor();";
                break;
            case "firefox":
                by = "java.lang.Runtime.getRuntime().exec(\"execute/UpLoadFile.exe 文件上传 " + locatorValue + "\").waitFor();";
                break;
            case "chrome":
                by = "java.lang.Runtime.getRuntime().exec(\"execute/UpLoadFile.exe 打开 " + locatorValue + "\").waitFor();";
                break;
            default:
                logger.error("无法识别定位选择器");
                throw new NotFoundLocatorException("无法识别定位选择器: " + locatorValue);
        }
        return by;
    }
}
