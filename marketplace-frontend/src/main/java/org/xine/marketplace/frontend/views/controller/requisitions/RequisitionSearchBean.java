package org.xine.marketplace.frontend.views.controller.requisitions;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.xine.marketplace.business.services.requisitions.RequisitionService;
import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.entities.RequisitionStatus;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.model.filters.RequisitionFilterOrderBy;
import org.xine.marketplace.model.filters.RequisitionFilterOrderBy.RequisitionFilterOrderByType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    /** The requisition service. */
    @Inject
    private RequisitionService requisitionService;

    // ~ Model Variables
    // ========================================================================================================

    // private List<Requisition> requisitions;

    /** The filter. */
    private RequisitionFilter filter;

    /** The requisition status. */
    private RequisitionStatus[] requisitionStatus;

    /** The model. */
    private LazyDataModel<Requisition> model;

    // ~ Constructors and it Callbacks
    // ========================================================================================================

    /**
     * Inits the.
     */
    @PostConstruct
    private void postConstruct() {
        this.requisitionStatus = RequisitionStatus.values();
        this.filter = new RequisitionFilter();

        this.model = new LazyDataModel<Requisition>() {

            private static final long serialVersionUID = 1L;

            private static final String ID = "id";
            private static final String TOTAL_VALUE = "totalValue";
            private static final String CREATION_DATE = "creationDate";
            private static final String SELLER_USERNAME = "seller.username";
            private static final String CLIENT_NAME = "client.name";

            @SuppressWarnings("synthetic-access")
            @Override
            public List<Requisition> load(final int first, final int pageSize,
                    final String sortField, final SortOrder sortOrder,
                    final Map<String, Object> filters) {

                getFilter().setFirstResult(first);
                getFilter().setMaxResults(pageSize);

                if (sortField != null) {
                    getFilter().setOrderBy(new RequisitionFilterOrderBy());
                    getFilter().getOrderBy().setDescendent(SortOrder.DESCENDING.equals(sortOrder));
                    switch (sortField) {
                    case ID:
                        getFilter().getOrderBy().setOrderByType(RequisitionFilterOrderByType.ID);
                        break;
                    case CLIENT_NAME:
                        getFilter().getOrderBy().setOrderByType(
                                RequisitionFilterOrderByType.CLIENT_NAME);
                        break;
                    case SELLER_USERNAME:
                        getFilter().getOrderBy().setOrderByType(
                                RequisitionFilterOrderByType.SELLER_NAME);
                        break;
                    case CREATION_DATE:
                        getFilter().getOrderBy().setOrderByType(
                                RequisitionFilterOrderByType.CREATION_DATE);
                        break;
                    case TOTAL_VALUE:
                        getFilter().getOrderBy().setOrderByType(RequisitionFilterOrderByType.VALUE);
                        break;
                    default:
                        getFilter().getOrderBy().setOrderByType(null);
                        break;
                    }
                }

                final int count = RequisitionSearchBean.this.requisitionService
                        .filtrateCount(getFilter());
                setRowCount(count);
                return RequisitionSearchBean.this.requisitionService.filtrate(getFilter());
            }
        };

    }

    // ~ Methods Handlers
    // ========================================================================================================

    // public void search() {
    // // this.requisitions = this.requisitionService.search(this.filter);
    // }

    /**
     * define style of the XLS document.
     * @param document
     *            document to define style
     */
    @SuppressWarnings("static-method")
    public void exportToXls(final Object document) {
        final HSSFWorkbook documentPoi = (HSSFWorkbook) document;

        final HSSFSheet firtsPage = documentPoi.getSheetAt(0);
        final HSSFRow header = firtsPage.getRow(0);
        final HSSFCellStyle style = documentPoi.createCellStyle();

        final HSSFFont headerFont = documentPoi.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 16);

        style.setFont(headerFont);
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            header.getCell(i).setCellStyle(style);
        }
    }

    // ~ Getters and Setters properties
    // ========================================================================================================
    // public List<Requisition> getRequisitions() {
    // return this.requisitions;
    // }

    // public void setRequisitions(final List<Requisition> requisitions) {
    // this.requisitions = requisitions;
    // }

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

    /**
     * Gets the model.
     * @return the model
     */
    public LazyDataModel<Requisition> getModel() {
        return this.model;
    }

    /**
     * Checks if is empty.
     * @return true, if is empty
     */
    public boolean isModelEmpty() {
        // #{empty requisitionSearchBean.requisitions}
        return this.model.getRowCount() == 0;
    }

    /**
     * Sets the size.
     * @param value
     *            the new size
     */

}
