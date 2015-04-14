package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Default;

/**
 * The Interface ProductService.
 */
@Default
public class ProductService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Save.
     * @param poduct
     *            the poduct
     * @return the product
     * @throws BusinessException
     *             the business exception
     */
    public Product save(final Product poduct) {
        System.out.println("tetsing titititti A");
        return null;
    }

    /**
     * Search.
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    List<Product> search() {
        return null;
    }

}
