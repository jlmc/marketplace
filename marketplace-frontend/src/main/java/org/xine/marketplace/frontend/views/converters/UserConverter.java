package org.xine.marketplace.frontend.views.converters;

import org.xine.marketplace.frontend.views.util.helpers.Strings;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.daos.UsersRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * The Class UserConverter.
 */
@FacesConverter(forClass = User.class)
public class UserConverter implements Converter {

    /** The repository. */
    @Inject
    private UsersRepository repository;

    // public UserConverter() {
    // // using this because the CDI don't works in FacesConverter
    // this.repository = CDIServiceLocator.getBean(UsersRepository.class);
    // }

    /**
     * Gets the as object.
     * @param context
     *            the context
     * @param component
     *            the component
     * @param value
     *            the value
     * @return the as object
     */
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component,
            final String value) {
        User result = null;
        if (Strings.isNotNullOrBlank(value)) {
            final Long id = new Long(value);
            result = this.repository.getById(id);
        }
        return result;
    }

    /**
     * Gets the as string.
     * @param context
     *            the context
     * @param component
     *            the component
     * @param value
     *            the value
     * @return the as string
     */
    @Override
    public String getAsString(final FacesContext context, final UIComponent component,
            final Object value) {
        if (value != null) {
            final User user = (User) value;
            return user.getId() == null ? null : user.getId().toString();
        }
        return "";
    }
}
