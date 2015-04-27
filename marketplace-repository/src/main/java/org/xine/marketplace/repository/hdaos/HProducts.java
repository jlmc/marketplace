package org.xine.marketplace.repository.hdaos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.model.filters.ProductFilter;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * The Class HProducts.
 */
public class HProducts {

    /** The manger. */
    @Inject
    EntityManager manger;

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<Product> search(final ProductFilter filter) {
        final Session session = this.manger.unwrap(Session.class);

        final Criteria criteria = session.createCriteria(Product.class);

        if (haveSKU(filter)) {
            // where sku = :?
            criteria.add(Restrictions.eq("sku", filter.getSku()));
        }

        if (haveName(filter)) {
            // where name like '%texto%'
            criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("name")).list();
    }

    /**
     * Gets the by sku.
     * @param sku
     *            the sku
     * @return the by sku
     */
    @SuppressWarnings("unchecked")
    public List<Product> getBySKU(final String sku) {
        final Session session = this.manger.unwrap(Session.class);

        final Criteria criteria = session.createCriteria(Product.class);

        criteria.add(Restrictions.eq("sku", "" + sku).ignoreCase());

        return criteria.list();
    }

    /**
     * Have sku.
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getSku()} is diferent of null
     *         and is not empty, false otherwise.
     */
    private static boolean haveSKU(final ProductFilter filter) {
        return filter != null && filter.getSku() != null && !filter.getSku().trim().isEmpty();
    }

    /**
     * Have name.
     * @param filter
     *            the filter
     * @return true, if the {@code ProductFilter#getName()} is diferent of null
     *         and is not empty, false otherwise.
     */
    private static boolean haveName(final ProductFilter filter) {
        return filter != null && filter.getName() != null && !filter.getName().trim().isEmpty();
    }

    public void setManager(final EntityManager entityManager) {
        this.manger = entityManager;

    }
}
