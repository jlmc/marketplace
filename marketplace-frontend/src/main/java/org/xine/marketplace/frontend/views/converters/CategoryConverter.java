package org.xine.marketplace.frontend.views.converters;

import org.apache.commons.lang.StringUtils;
import org.xine.marketplace.frontend.views.util.cdi.CDIServiceLocator;
import org.xine.marketplace.model.entities.Category;
import org.xine.marketplace.repository.daos.CategorysRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * The Class CategoryConverter.
 */
@FacesConverter(forClass = Category.class)
public class CategoryConverter implements Converter {

    private final CategorysRepository repository;

    public CategoryConverter() {
        // using this because the CDI don't works in FacesConverter
        this.repository = CDIServiceLocator.getBean(CategorysRepository.class);
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        Category result = null;

        if (value != null && StringUtils.isNotEmpty(value)) {
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
            final Category p = (Category) value;
            // use the id
            return p.getId() == null ? null : p.getId().toString();
        }
        return "";

    }
}
