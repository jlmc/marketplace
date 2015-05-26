package org.xine.marketplace.business.services.requisitions;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.xine.marketplace.model.filters.RequisitionActivityFilter;
import org.xine.marketplace.model.vo.RequisitionActivity;
import org.xine.marketplace.repository.daos.RequisitionsRepository;

/**
 * The Class RequisitionActivityService.
 */
@Default
public class RequisitionActivityService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisitions repository. */
    @Inject
    private RequisitionsRepository requisitionsRepository;

    /**
     * Gets the requisition activity.
     * @param filter
     *            the filter
     * @return the requisition activity
     */
    public RequisitionActivity getRequisitionActivity(final RequisitionActivityFilter filter) {

        final RequisitionActivityFilter f = (filter == null) ? new RequisitionActivityFilter() : filter;

        final RequisitionActivity requisitionActivity = new RequisitionActivity();
        requisitionActivity.setGlobalTotals(this.requisitionsRepository.getTotalByDate(f));

        return requisitionActivity;
    }

}
