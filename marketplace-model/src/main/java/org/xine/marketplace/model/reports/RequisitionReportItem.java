package org.xine.marketplace.model.reports;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RequisitionReportItem implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    private Long id;

    /** The client name. */
    private String clientName;

    /** The seller name. */
    private String sellerName;

    /** The creation date. */
    private Date creationDate;

    /** The total. */
    private BigDecimal total;

    /**
     * Instantiates a new requisition report item.
     */
    public RequisitionReportItem() {
    }

    /**
     * Instantiates a new requisition report item.
     * @param id
     *            the id
     * @param clientName
     *            the client name
     * @param sellerName
     *            the seller name
     * @param creationDate
     *            the creation date
     * @param total
     *            the total
     */
    public RequisitionReportItem(final Long id, final String clientName, final String sellerName, final Date creationDate, final BigDecimal total) {
        super();
        this.id = id;
        this.clientName = clientName;
        this.sellerName = sellerName;
        this.creationDate = creationDate;
        this.total = total;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
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
     * Gets the creation date.
     * @return the creationDate
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Sets the creation date.
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the total.
     * @return the total
     */
    public BigDecimal getTotal() {
        return this.total;
    }

    /**
     * Sets the total.
     * @param total
     *            the total to set
     */
    public void setTotal(final BigDecimal total) {
        this.total = total;
    }

}
