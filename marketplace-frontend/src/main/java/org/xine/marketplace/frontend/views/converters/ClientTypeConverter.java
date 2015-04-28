package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.frontend.views.util.helpers.Strings;
import org.xine.marketplace.model.entities.ClientType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * The Class ClientTypeConverter.
 */
@FacesConverter(forClass = ClientType.class)
public class ClientTypeConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        ClientType result = null;

        if (value != null && Strings.isNotNullOrBlank(value)) {
            result = ClientType.valueOf(value);
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
            final ClientType ct = (ClientType) value;
            return ct.toString();
        }
        return "";
    }

}
