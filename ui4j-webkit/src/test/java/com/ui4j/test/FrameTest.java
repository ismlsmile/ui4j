package com.ui4j.test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ui4j.api.browser.BrowserEngine;
import com.ui4j.api.browser.BrowserFactory;
import com.ui4j.api.browser.BrowserType;
import com.ui4j.api.browser.Page;
import com.ui4j.api.dom.Document;
import com.ui4j.api.dom.Element;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FrameTest {

    private static Document document;

    @BeforeClass public static void beforeTest() {
        String url = FrameTest.class.getResource("/Frame.html").toExternalForm();
        BrowserEngine browser = BrowserFactory.getBrowser(BrowserType.WebKit);
        Page page = browser.navigate(url);
        page.show();
        document = page.getDocument();
    }

    @Test public void test() {
        List<Element> frames = document.queryAll("frame");

        Document documentFrame1 = frames.get(0).getContentDocument();
        Document documentFrame2 = frames.get(1).getContentDocument();

        Assert.assertEquals("Frame1", documentFrame1.query("div").get().getInnerHTML());
        Assert.assertEquals("Frame2", documentFrame2.query("div").get().getInnerHTML());
    }
}
