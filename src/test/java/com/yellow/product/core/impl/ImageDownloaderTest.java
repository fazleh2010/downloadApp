/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellow.product.core.impl;

import com.yellow.product.core.api.DownLoadManager;
import com.yellow.product.core.constant.Message;
import com.yellow.product.core.impl.ImageDownloadReport.Status;
import com.yellow.product.exception.LoaderException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
public class ImageDownloaderTest extends TestCase {

    private String downloadLocation = "src/test/resources";
    private File inputFile = new File(downloadLocation + File.separator + "linksSuccess.txt.txt");

    public ImageDownloaderTest() throws LoaderException {

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of download method, of class ImageDownloader. When Url is correct.
     * Imgage found. Image downloaded successfully
     */
    public void testDownload_WhenSuccess() throws Exception {
        System.out.println("testDownload_WhenSuccess");
        inputFile = new File(downloadLocation + File.separator + "linksSuccess.txt");
        DownLoadManager ImagedownLoadManager = new ImageDownloadManager(inputFile);
        ImageDownloadReport report = new ImageDownloadReport(null, Boolean.TRUE, null);
        List<ImageDownloadReport> expResult = new ArrayList<ImageDownloadReport>();
        expResult.add(report);
        Status status = expResult.iterator().next().getstatus();
        Report result = ImagedownLoadManager.download();
        ImagedownLoadManager.display();
        assertEquals(status.SUCCESS, result.getreports().iterator().next().getstatus());
    }

    /**
     * Test of download method, of class ImageDownloader. When no image found in
     * url. Url not found Download fail
     */
    public void testDownload_WhenFail() throws Exception {
        System.out.println("testDownload_WhenFail");
        inputFile = new File(downloadLocation + File.separator + "linksFail.txt");
        DownLoadManager ImagedownLoadManager = new ImageDownloadManager(inputFile);
        ImageDownloadReport report = new ImageDownloadReport(null, Boolean.FALSE, Message.FAIL_DOWNLOAD_NO_IMAGE);
        List<ImageDownloadReport> expResult = new ArrayList<ImageDownloadReport>();
        expResult.add(report);
        Status status = expResult.iterator().next().getstatus();
        Report result = ImagedownLoadManager.download();
        ImagedownLoadManager.display();
        assertEquals(status.FAIL, result.getreports().iterator().next().getstatus());
    }

}
