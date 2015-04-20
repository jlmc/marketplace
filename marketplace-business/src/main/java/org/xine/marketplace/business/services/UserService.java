package org.xine.marketplace.business.services;

import org.apache.velocity.tools.generic.NumberTool;
import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.business.util.mail.Mailer;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.daos.PermissionsRepository;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.util.Transactional;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

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
    private UsersRepository repository;

    /** The permissions repository. */
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
            if (this.repository.getUserByEmail(user.getEmail()) != null) {
                throw new BusinessException(String.format("Email '%s'  Already in use.",
                        user.getEmail()));
            }

            final User createdUser = this.repository.save(user);

            if (isAdd) {
                // send Email to the User...
                sendEmail(createdUser);
            }
            return createdUser;
        } catch (final RepositoryException e) {
            throw new BusinessException("Could't not save the user.", e.getCause());
        }

    }

    /**
     * Send email.
     * @param user
     *            the user
     */
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
     * Sets the repository.
     * @param repository
     *            the new repository
     */
    protected void setRepository(final UsersRepository repository) {
        this.repository = repository;
    }

    /**
     * Sets the permissions repository.
     * @param permissionsRepository
     *            the new permissions repository
     */
    public void setPermissionsRepository(final PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }

}
