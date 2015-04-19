package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.util.Transactional;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * The Class UserService.
 */
@Default
public class UserService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Inject
    private UsersRepository repository;

    @Transactional
    public User save(final User user) throws BusinessException {

        try {
            // We can check if the username or email avariable.

            return this.repository.save(user);
        } catch (final RepositoryException e) {
            throw new BusinessException("Could't not save the user.", e.getCause());
        }

    }

    protected UsersRepository getRepository() {
        return this.repository;
    }

    protected void setRepository(final UsersRepository repository) {
        this.repository = repository;
    }

}
