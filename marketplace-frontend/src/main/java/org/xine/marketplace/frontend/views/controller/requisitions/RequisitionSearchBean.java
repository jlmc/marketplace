package org.xine.marketplace.frontend.views.controller.requisitions;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.xine.marketplace.business.services.requisitions.RequisitionService;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.model.filters.RequisitionFilter;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class RequisitionSearchBean.
 * @author Joao Costa
 */
@Named
@ViewScoped
public class RequisitionSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // ~ business services
    // ========================================================================================================
    @Inject
    private RequisitionService requisitionService;

    // ~ Model Variables
    // ========================================================================================================

    /** The requisitions. */
    private List<Requisition> requisitions;

    /** The filter. */
    private RequisitionFilter filter;

    private RequisitionStatus[] requisitionStatus;

    // ~ Constructors and it Callbacks
    // ========================================================================================================

    /**
     * Inits the.
     */
    @PostConstruct
    private void postConstruct() {
        this.requisitionStatus = RequisitionStatus.values();
        this.filter = new RequisitionFilter();

    }

    // ~ Methods Handlers
    // ========================================================================================================
    /**
     * Search.
     */
    public void search() {
        System.out.println("Search operation");
        this.requisitions = this.requisitionService.search(this.filter);
    }

    /**
     * define style of the XLS document
     * @param document
     *            document to define style
     */
    @SuppressWarnings("static-method")
    public void exportToXls(final Object document) {
        final HSSFWorkbook documentPoi = (HSSFWorkbook) document;

        // getFirts page
        final HSSFSheet firtsPage = documentPoi.getSheetAt(0);
        final HSSFRow header = firtsPage.getRow(0);
        final HSSFCellStyle style = documentPoi.createCellStyle();

        final HSSFFont headerFont = documentPoi.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 16);

        style.setFont(headerFont);
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            header.getCell(i).setCellStyle(style);
        }
    }

    // -------------------------------------------------------------------------
    //
    // Getters and Setters properties
    //
    // -------------------------------------------------------------------------
    /**
     * Gets the requisitions.
     * @return the requisitions
     */
    public List<Requisition> getRequisitions() {
        return this.requisitions;
    }

    /**
     * Sets the requisitions.
     * @param requisitions
     *            the new requisitions
     */
    public void setRequisitions(final List<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    /**
     * Gets the filter.
     * @return the filter
     */
    public RequisitionFilter getFilter() {
        return this.filter;
    }

    /**
     * Sets the filter.
     * @param filter
     *            the filter to set
     */
    public void setFilter(final RequisitionFilter filter) {
        this.filter = filter;
    }

    /**
     * Gets the requisition status.
     * @return the requisitionStatus
     */
    public RequisitionStatus[] getRequisitionStatus() {
        return this.requisitionStatus;
    }

}
