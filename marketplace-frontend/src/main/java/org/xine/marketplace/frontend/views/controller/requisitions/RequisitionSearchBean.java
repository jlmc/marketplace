package org.xine.marketplace.frontend.views.controller.requisitions;

import org.xine.marketplace.business.services.RequisitionService;
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
 */
@Named
@ViewScoped
public class RequisitionSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    //
    // business services
    //
    // -------------------------------------------------------------------------
    @Inject
    private RequisitionService requisitionService;

    // -------------------------------------------------------------------------
    //
    // Model Variables
    //
    // -------------------------------------------------------------------------

    /** The requisitions. */
    private List<Requisition> requisitions;

    /** The filter. */
    private RequisitionFilter filter;

    private RequisitionStatus[] requisitionStatus;

    // -------------------------------------------------------------------------
    //
    // Constructors and it Callbacks
    //
    // -------------------------------------------------------------------------

    /**
     * Inits the.
     */
    @PostConstruct
    private void postConstruct() {
        this.requisitionStatus = RequisitionStatus.values();
        this.filter = new RequisitionFilter();

        // System.out.println("SearchOrdersBean PostConstruct");
        // this.requisitions = new ArrayList<>();
        // for (int i = 0; i < 50; i++) {
        // this.requisitions.add(new Requisition(Long.valueOf(i + 1)));
        // }
    }

    // -------------------------------------------------------------------------
    //
    // Methods Handlers
    //
    // -------------------------------------------------------------------------
    /**
     * Search.
     */
    public void search() {
        System.out.println("Search operation");
        this.requisitions = this.requisitionService.search(this.filter);
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
