package org.xine.marketplace.frontend.views.controller;

import org.xine.marketplace.business.services.UserService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class UserSaverBean.
 */
@Named
@ViewScoped
public class UserSaverBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	//-------------------------------------------------------------------------
	//
	//  business services
	//
	//-------------------------------------------------------------------------


	/** The service. */
	@Inject
	private UserService service;


	//-------------------------------------------------------------------------
	//
	//  Model properties
	//
	//-------------------------------------------------------------------------


	/** The user. */
	private User user;

	/** The permissions. */
	private List<Permission> permissions;


	//-------------------------------------------------------------------------
	//
	//  Single operation
	//
	//-------------------------------------------------------------------------
	/**
	 * Initialize.
	 * the single operations
	 */
	@PostConstruct
	private void initialize() {
		System.out.println("->initialize @PostConstruct");
		this.permissions = this.service.getPermissions();
		this.clean();
	}

	
	/**
	 * Clean.
	 */
	private void clean() {
		this.user = new User();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Event handlers
	//
	//--------------------------------------------------------------------------
	/**
	 * Save operation.
	 */
	public void save() {
		this.user = this.service.save(this.user);
		FacesUtil.addInfoMessage("User created with sucess.");
		clean();
	}

	

	/**
	 * Gets the user.
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 * @param user
	 *            the new user
	 */
	public void setUser(final User user) {
		this.user = user;
	}

	/**
	 * Gets the permissions.
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return this.permissions;
	}

}
