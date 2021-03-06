/**
 * <h1>Report</h1>
 * The Report class store the result of all urls.
 */
package com.yellow.product.core.impl;

import com.yellow.product.utils.FileNameUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohammad Fazleh Elahi
 */
@XmlRootElement(name = "report")
@XmlAccessorType(XmlAccessType.FIELD)
public class Report {

    private static final String REPORT = "_Report.xml";
    private String input;
    private String downloadReport;
    private String downloadLocation;

    @XmlElement(name = "element")
    private List<ImageDownloadReport> reports = new ArrayList<ImageDownloadReport>();

    public Report() {
    }

    public Report(String downloadLocation, String inputFileName, List<ImageDownloadReport> reports) {
        this.input = downloadLocation + File.separator + inputFileName;
        this.downloadLocation = downloadLocation;
        this.downloadReport = downloadLocation + File.separator + FileNameUtils.getFileNameWithoutExtension(inputFileName) + REPORT;
        this.reports = reports;
    }

    public List<ImageDownloadReport> getreports() {
        return reports;
    }

    public String getDownloadReport() {
        return downloadReport;
    }

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public String getInput() {
        return input;
    }

}
