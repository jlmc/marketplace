package org.xine.marketplace.model.filters;

import org.xine.marketplace.model.entities.RequisitionStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class RequisitionFilter.
 */
public class RequisitionFilter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The number start. */
    private Long numberStart;

    /** The number end. */
    private Long numberEnd;

    /** The creation date start. */
    private Date creationDateStart;

    /** The creation date end. */
    private Date creationDateEnd;

    /** The seller name. */
    private String sellerName;

    /** The client name. */
    private String clientName;

    /** The status. */
    private RequisitionStatus[] status;

    /** The firts record. */
    private int firstResult;

    /** The number of records. */
    private int maxResults;

    /** The order by. */
    private RequisitionFilterOrderBy orderBy;

    /**
     * Gets the number start.
     * @return the numberStart
     */
    public Long getNumberStart() {
        return this.numberStart;
    }

    /**
     * Sets the number start.
     * @param numberStart
     *            the numberStart to set
     */
    public void setNumberStart(final Long numberStart) {
        this.numberStart = numberStart;
    }

    /**
     * Gets the number end.
     * @return the numberEnd
     */
    public Long getNumberEnd() {
        return this.numberEnd;
    }

    /**
     * Sets the number end.
     * @param numberEnd
     *            the numberEnd to set
     */
    public void setNumberEnd(final Long numberEnd) {
        this.numberEnd = numberEnd;
    }

    /**
     * Gets the creation date start.
     * @return the creationDateStart
     */
    public Date getCreationDateStart() {
        return this.creationDateStart;
    }

    /**
     * Sets the creation date start.
     * @param creationDateStart
     *            the creationDateStart to set
     */
    public void setCreationDateStart(final Date creationDateStart) {
        this.creationDateStart = creationDateStart;
    }

    /**
     * Gets the creation date end.
     * @return the creationDateEnd
     */
    public Date getCreationDateEnd() {
        return this.creationDateEnd;
    }

    /**
     * Sets the creation date end.
     * @param creationDateEnd
     *            the creationDateEnd to set
     */
    public void setCreationDateEnd(final Date creationDateEnd) {
        this.creationDateEnd = creationDateEnd;
    }

    /**
     * Gets the seller name.
     * @return the sellerName
     */
    public String getSellerName() {
        return this.sellerName;
    }

    /**
     * Sets the seller name.
     * @param sellerName
     *            the sellerName to set
     */
    public void setSellerName(final String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * Gets the client name.
     * @return the clientName
     */
    public String getClientName() {
        return this.clientName;
    }

    /**
     * Sets the client name.
     * @param clientName
     *            the clientName to set
     */
    public void setClientName(final String clientName) {
        this.clientName = clientName;
    }

    /**
     * Gets the status.
     * @return the status
     */
    public RequisitionStatus[] getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     * @param status
     *            the status to set
     */
    public void setStatus(final RequisitionStatus[] status) {
        this.status = status;
    }

    /**
     * Gets the first result.
     * @return the first result
     */
    public int getFirstResult() {
        return this.firstResult;
    }

    /**
     * Sets the first result.
     * @param firstResult
     *            the new first result
     */
    public void setFirstResult(final int firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * Gets the max results.
     * @return the max results
     */
    public int getMaxResults() {
        return this.maxResults;
    }

    /**
     * Sets the max results.
     * @param maxResults
     *            the new max results
     */
    public void setMaxResults(final int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Gets the order by.
     * @return the order by
     */
    public RequisitionFilterOrderBy getOrderBy() {
        return this.orderBy;
    }

    /**
     * Sets the order by.
     * @param orderBy
     *            the new order by
     */
    public void setOrderBy(final RequisitionFilterOrderBy orderBy) {
        this.orderBy = orderBy;
    }

}
