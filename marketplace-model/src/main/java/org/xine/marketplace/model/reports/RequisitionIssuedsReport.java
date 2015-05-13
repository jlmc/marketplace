package org.xine.marketplace.model.reports;

import java.io.Serializable;

/**
 * The Class RequisitionIssuedsReport.
 */
public class RequisitionIssuedsReport implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The file. */
    private byte[] file;

    /** The file name. */
    private String fileName;

    /** The file extention. */
    private String fileExtention;

    /**
     * Gets the file.
     * @return the file
     */
    public byte[] getFile() {
        return this.file;
    }

    /**
     * Sets the file.
     * @param file
     *            the new file
     */
    public void setFile(final byte[] file) {
        this.file = file;
    }

    /**
     * Gets the file name.
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the file name.
     * @param fileName
     *            the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the file extention.
     * @return the file extention
     */
    public String getFileExtention() {
        return this.fileExtention;
    }

    /**
     * Sets the file extention.
     * @param fileExtention
     *            the new file extention
     */
    public void setFileExtention(final String fileExtention) {
        this.fileExtention = fileExtention;
    }

}
