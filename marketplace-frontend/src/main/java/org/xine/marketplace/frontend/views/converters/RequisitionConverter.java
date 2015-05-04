package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.business.services.RequisitionService;
import org.xine.marketplace.frontend.views.util.helpers.Strings;
import org.xine.marketplace.model.entities.Requisition;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * The Class CategoryConverter.
 */
@FacesConverter(forClass = Requisition.class)
public class RequisitionConverter implements Converter {

    /** The service. */
    @Inject
    private RequisitionService service;

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        Requisition result = null;
        if (Strings.isNotNullOrBlank(value)) {
            final Long id = new Long(value);
            result = this.service.getById(id);
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
            final Requisition requisition = (Requisition) value;
            // use the id
            return requisition.getId() == null ? null : requisition.getId().toString();
        }
        return null;
    }

}
