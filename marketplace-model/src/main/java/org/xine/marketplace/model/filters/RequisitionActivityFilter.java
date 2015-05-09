package org.xine.marketplace.model.filters;

import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.User;

import java.io.Serializable;

/**
 * The Class TotalRequisitionFilter.
 */
public class RequisitionActivityFilter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The seller. */
    private User seller;

    /** The client. */
    private Client client;

    /** The number of days. */
    private Integer numberOfDays = Integer.valueOf(15);

    /**
     * Instantiates a new total requisition filter.
     */
    public RequisitionActivityFilter() {
        super();
    }

    /**
     * Instantiates a new total requisition filter.
     * @param seller
     *            the seller
     * @param client
     *            the client
     * @param numberOfDays
     *            the number of days
     */
    public RequisitionActivityFilter(final User seller, final Client client,
            final Integer numberOfDays) {
        super();
        this.seller = seller;
        this.client = client;
        this.numberOfDays = numberOfDays;
    }

    /**
     * Gets the seller.
     * @return the seller
     */
    public User getSeller() {
        return this.seller;
    }

    /**
     * Sets the seller.
     * @param seller
     *            the seller to set
     */
    public void setSeller(final User seller) {
        this.seller = seller;
    }

    /**
     * Gets the client.
     * @return the client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Sets the client.
     * @param client
     *            the client to set
     */
    public void setClient(final Client client) {
        this.client = client;
    }

    /**
     * Gets the number of days.
     * @return the numberOfDays
     */
    public Integer getNumberOfDays() {
        return this.numberOfDays;
    }

    /**
     * Sets the number of days.
     * @param numberOfDays
     *            the numberOfDays to set
     */
    public void setNumberOfDays(final Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    /**
     * Checks if is seller.
     * @return true, if is seller
     */
    public boolean isSeller() {
        return this.seller != null && this.seller.getId() != null;
    }

    /**
     * Checks if is client.
     * @return true, if is client
     */
    public boolean isClient() {
        return this.client != null && this.client.getId() != null;
    }

    /**
     * Checks if is number of days.
     * @return true, if is number of days
     */
    public boolean isNumberOfDays() {
        return this.numberOfDays != null && this.numberOfDays.intValue() >= 0;
    }

}
