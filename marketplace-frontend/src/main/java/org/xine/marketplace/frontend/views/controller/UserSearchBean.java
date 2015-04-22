package org.xine.marketplace.frontend.views.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.marketplace.business.services.UserService;
import org.xine.marketplace.model.entities.User;

/**
 * The Class UserSearchBean.
 */
@Named
@ViewScoped
public class UserSearchBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    //
    // business services
    //
    // -------------------------------------------------------------------------

    /** The service. */
    @Inject
    private UserService service;

    // -------------------------------------------------------------------------
    //
    // Model Variables
    //
    // -------------------------------------------------------------------------
    /** The users. */
    private List<User> users;

    // -------------------------------------------------------------------------
    //
    // Getters and Setters properties
    //
    // -------------------------------------------------------------------------
    /**
     * Gets the users.
     * 
     * @return the users
     */
    public List<User> getUsers() {
	return this.users;
    }

    /**
     * Sets the users.
     * 
     * @param users
     *            the new users
     */
    public void setUsers(final List<User> users) {
	this.users = users;
    }

    // -------------------------------------------------------------------------
    //
    // Methods
    //
    // -------------------------------------------------------------------------

}
