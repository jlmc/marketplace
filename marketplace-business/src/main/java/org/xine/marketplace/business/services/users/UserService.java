package org.xine.marketplace.business.services.users;

import org.apache.velocity.tools.generic.NumberTool;
import org.xine.email.api.MailMessage;
import org.xine.email.impl.templating.velocity.VelocityTemplate;
import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.business.util.mail.Mailer;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.UserFilter;
import org.xine.marketplace.repository.daos.PermissionsRepository;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class UserService.
 */
@Default
public class UserService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The repository. */
    @Inject
    private UsersRepository userRepository;

    /** The permissions repository. */
    @Inject
    private PermissionsRepository permissionsRepository;

    /** The mailer. */
    @Inject
    private Mailer mailer;

    /**
     * Save.
     * @param user
     *            the user
     * @return the user
     * @throws BusinessException
     *             the business exception
     */
    @Transactional
    public User save(final User user) throws BusinessException {

        try {

            final boolean isAdd = user.getId() == null;

            // We can check if the email avariable.
            // BR: 2 the email must be unique
            if (isAdd && this.userRepository.getUserByEmail(user.getEmail()) != null) {
                throw new BusinessException(String.format("Email '%s'  Already in use.",
                        user.getEmail()));
            }

            final User createdUser = this.userRepository.save(user);

            if (isAdd) {
                // send Email to the User...
                // sendEmail(createdUser);
            }
            return createdUser;
        } catch (final RepositoryException e) {
            if (RepositoryException.Type.CONCURRENCE.equals(e.getType())) {
                throw new BusinessException(e.getMessage());
            }
            throw new BusinessException("Could't not save the user.", e.getCause());
        }
    }

    /**
     * Delete.
     * @param user
     *            the user
     */
    @Transactional
    public void delete(final User user) {
        try {
            this.userRepository.delete(user);
        } catch (final RepositoryException e) {
            throw new BusinessException("Can't Delete the user with the Email " + user.getEmail());
        }

    }

    /**
     * Gets the user by email.
     * @param email
     *            the email
     * @return the user by email
     */
    public User getUserByEmail(final String email) {
        return this.userRepository.getUserByEmail(email, true);
    }

    /**
     * Search.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<User> search(final UserFilter filter) {
        return this.userRepository.search(filter);
    }

    /**
     * Send email.
     * @param user
     *            the user
     */
    @SuppressWarnings("unused")
    private void sendEmail(final User user) {
        if (this.mailer != null) {

            final MailMessage mailMessage = this.mailer.createMesage();
            mailMessage
            .to(user.getEmail())
            .subject("User creation sucess")
            // .bodyHtml("O user foi criado com sucesso")
                    .bodyHtml(
                    new VelocityTemplate(this.getClass().getResourceAsStream(
                            "/emails/createdUser.template"))).put("user", user)
                            .put("numberTool", new NumberTool()).put("locale", new Locale("pt", "PT"))
                            .send();

        } else {
            System.out.println("No email System configurated");
        }
    }

    /**
     * Gets the permissions.
     * @return the permissions
     */
    public List<Permission> getPermissions() {
        return this.permissionsRepository.getPermissions();
    }

    /**
     * Gets the permission by id.
     * @param id
     *            the id
     * @return the permission by id
     */
    public Permission getPermissionById(final Long id) {
        return this.permissionsRepository.getPermissionById(id);
    }

    // -------------------------------------------------------------------------
    //
    // Getters and Setters operation
    //
    // -------------------------------------------------------------------------
    /**
     * Gets the user repository.
     * @return the user repository
     */
    protected UsersRepository getUserRepository() {
        return this.userRepository;
    }

    /**
     * Sets the user repository.
     * @param userRepository
     *            the new user repository
     */
    protected void setUserRepository(final UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets the permissions repository.
     * @return the permissions repository
     */
    protected PermissionsRepository getPermissionsRepository() {
        return this.permissionsRepository;
    }

    /**
     * Sets the permissions repository.
     * @param permissionsRepository
     *            the new permissions repository
     */
    protected void setPermissionsRepository(final PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }
}
