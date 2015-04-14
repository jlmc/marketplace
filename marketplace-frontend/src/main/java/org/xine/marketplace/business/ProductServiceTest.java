package org.xine.marketplace.business;

import org.xine.marketplace.model.entities.Product;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface ProductService.
 */
public class ProductServiceTest implements Serializable {

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
    @SuppressWarnings("static-method")
    public Product save(final Product poduct) throws BusinessException {
        System.out.println("tetsing titititti ");
        return null;
    }

    /**
     * Search.
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("static-method")
    List<Product> search() throws BusinessException {
        return null;
    }

}
