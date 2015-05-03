package org.xine.marketplace.business.services;

import org.xine.marketplace.model.entities.Requisition;
import org.xine.marketplace.model.filters.RequisitionFilter;
import org.xine.marketplace.repository.daos.RequisitionsRepository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class RequisitionService.
 */
@Default
public class RequisitionService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The requisitions repository. */
    @Inject
    private RequisitionsRepository requisitionsRepository;

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Requisition> search(final RequisitionFilter filter) {
        return this.requisitionsRepository.search(filter);
    }

}
