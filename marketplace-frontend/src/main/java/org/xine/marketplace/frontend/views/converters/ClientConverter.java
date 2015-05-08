package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.business.services.clients.ClientService;
import org.xine.marketplace.frontend.views.util.helpers.Strings;
import org.xine.marketplace.model.entities.Client;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * The Class ClientConverter.
 */
@FacesConverter(forClass = Client.class)
public class ClientConverter implements Converter {

    /** The service. */
    @Inject
    private ClientService service;

    /**
     * <p>
     * Convert the specified string value, which is associated with the specified
     * {@link UIComponent}, into a model data object that is appropriate for being stored during
     * the <em>Apply Request
     * Values</em> phase of the request processing lifecycle.
     * </p>
     * @param context
     *            {@link FacesContext} for the request being processed
     * @param component
     *            {@link UIComponent} with which this model object
     *            value is associated
     * @param value
     *            String value to be converted (may be <code>null</code>)
     * @return <code>null</code> if the value to convert is <code>null</code>,
     *         otherwise the result of the conversion
     * @throws ConverterException
     *             if conversion cannot be successfully
     *             performed
     * @throws NullPointerException
     *             if <code>context</code> or <code>component</code> is <code>null</code>
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        Client result = null;
        if (Strings.isNotNullOrBlank(value)) {
            final Long id = new Long(value);
            result = this.service.getClient(id);
        }
        return result;
    }

    /**
     * <p>
     * Convert the specified model object value, which is associated with the specified
     * {@link UIComponent}, into a String that is suitable for being included in the response
     * generated during the <em>Render Response</em> phase of the request processing lifeycle.
     * </p>
     * @param context
     *            {@link FacesContext} for the request being processed
     * @param component
     *            {@link UIComponent} with which this model object
     *            value is associated
     * @param value
     *            Model object value to be converted
     *            (may be <code>null</code>)
     * @return a zero-length String if value is <code>null</code>,
     *         otherwise the result of the conversion
     * @throws ConverterException
     *             if conversion cannot be successfully
     *             performed
     * @throws NullPointerException
     *             if <code>context</code> or <code>component</code> is <code>null</code>
     */
    @Override
    public String getAsString(final FacesContext context, final UIComponent component,
            final Object value) {
        if (value != null) {
            final Client p = (Client) value;
            return p.getId() == null ? null : p.getId().toString();
        }
        return "";
    }

}
