package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.model.entities.Requisition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * The Class RequisitionSearchBean.
 */
@Named
@ViewScoped
public class RequisitionSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisitions. */
    private List<Requisition> requisitions;

    /**
     * Inits the.
     */
    @PostConstruct
    private void init() {
        System.out.println("SearchOrdersBean PostConstruct");
        this.requisitions = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            this.requisitions.add(new Requisition(Long.valueOf(i + 1)));
        }
    }

    /**
     * Search.
     */
    @SuppressWarnings("static-method")
    public void search() {
        System.out.println("Search operation");
        System.out.println();

    }

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

}
