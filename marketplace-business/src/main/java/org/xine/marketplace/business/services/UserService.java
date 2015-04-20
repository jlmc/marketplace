package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.business.util.mail.Mailer;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.repository.daos.UsersRepository;
import org.xine.marketplace.repository.exceptions.RepositoryException;
import org.xine.marketplace.repository.util.Transactional;

import com.outjected.email.api.MailMessage;

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

	/** The repository. */
	@Inject
	private UsersRepository repository;
	
	/** The mailer. */
	@Inject
	private Mailer mailer;

	/**
	 * Save.
	 *
	 * @param user the user
	 * @return the user
	 * @throws BusinessException the business exception
	 */
	@Transactional
	public User save(final User user) throws BusinessException {

		try {

			boolean isAdd = user.getId() == null;

			// We can check if the username or email avariable.
			if( this.repository.getUserByUsername(user.getUsername()) != null){
				throw new BusinessException(
						String.format("Username '%s' Already in use.", 
								user.getUsername()));
			}
			if(this.repository.getUserByEmail(user.getEmail())!= null){
				throw new BusinessException(
						String.format("Email '%s'  Already in use.", 
								user.getEmail()));
			}

			User createdUser = this.repository.save(user);

			if(isAdd){
				//send Email to the User...
				this.sendEmail(createdUser);
			}
			return createdUser;
		} catch (final RepositoryException e) {
			throw new BusinessException("Could't not save the user.", e.getCause());
		}

	}


	/**
	 * Send email.
	 *
	 * @param createdUser the created user
	 */
	private void sendEmail(User user) {
		if(this.mailer!= null){
			
			MailMessage mailMessage = this.mailer.createMesage();
			mailMessage.to(user.getEmail())
						.subject("User creation sucess")
						.bodyHtml("O user foi criado com sucesso")
						.send();
			
			
		}else{
			System.out.println("No email System configurated");
		}

	}


	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	protected UsersRepository getRepository() {
		return this.repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	protected void setRepository(final UsersRepository repository) {
		this.repository = repository;
	}

}
