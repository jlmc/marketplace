package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.model.entities.Product;
import org.xine.marketplace.repository.daos.ProductsRepository;
import org.xine.marketplace.util.Strings;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * The Class CategoryConverter.
 */
@FacesConverter(forClass = Product.class)
public class ProductConverter implements Converter {

    /** The repository. */
    @Inject
    private ProductsRepository repository;

    // public ProductConverter() {
    // using this because the CDI don't works in FacesConverter
    // this.repository = CDIServiceLocator.getBean(ProductsRepository.class);
    // }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        Product result = null;
        if (Strings.isNotNullOrBlank(value)) {
            final Long id = new Long(value);
            result = this.repository.getById(id);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(final FacesContext context, final UIComponent component,
            final Object value) {
        if (value != null) {
            final Product p = (Product) value;
            // use the id
            return p.getId() == null ? null : p.getId().toString();
        }
        return "";
    }
}
