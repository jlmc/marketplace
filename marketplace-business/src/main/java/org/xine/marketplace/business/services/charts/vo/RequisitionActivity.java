package org.xine.marketplace.business.services.charts.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * The Class RequisitionActivity.
 */
public class RequisitionActivity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user totals. */
    private Map<Date, BigDecimal> userTotals;

    /** The global totals. */
    private Map<Date, BigDecimal> globalTotals;

    /**
     * @return the userTotals
     */
    public Map<Date, BigDecimal> getUserTotals() {
        return this.userTotals;
    }

    /**
     * @param userTotals
     *            the userTotals to set
     */
    public void setUserTotals(final Map<Date, BigDecimal> userTotals) {
        this.userTotals = userTotals;
    }

    /**
     * @return the globalTotals
     */
    public Map<Date, BigDecimal> getGlobalTotals() {
        return this.globalTotals;
    }

    /**
     * @param globalTotals
     *            the globalTotals to set
     */
    public void setGlobalTotals(final Map<Date, BigDecimal> globalTotals) {
        this.globalTotals = globalTotals;
    }

}
