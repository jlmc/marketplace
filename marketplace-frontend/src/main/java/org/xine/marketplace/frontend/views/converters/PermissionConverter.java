package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.business.services.users.UserService;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.util.Strings;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * The Class PermissionConverter.
 */
@FacesConverter(forClass = Permission.class)
public class PermissionConverter implements Converter {

    /** The service. */
    @Inject
    private UserService service;

    /*
     * (non-Javadoc)
     * @see
     * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
     * , javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        Permission result = null;
        if (Strings.isNotNullOrBlank(value)) {
            final Long id = new Long(value);
            result = this.service.getPermissionById(id);
        }
        // final Permission r = Optional.ofNullable(value).map(String::trim)
        // .filter(t -> t.length() > 0).map(s -> new Long(s)).map(id -> {
        // return this.service.getPermissionById(id);
        // }).orElse(null);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see
     * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
     * , javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(final FacesContext context, final UIComponent component,
            final Object value) {
        String result = "";
        // i what to do the next comment code, but this time i gone use Optional
        if (value != null) {
            final Permission p = (Permission) value;
            result = p.getId() == null ? null : p.getId().toString();
        }

        // result = Optional.ofNullable(value).map(p -> ((Permission)
        // p).getId())
        // .map(id -> id.toString()).orElse("");

        return result;
    }
}
