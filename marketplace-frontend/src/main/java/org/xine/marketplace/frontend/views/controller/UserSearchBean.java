package org.xine.marketplace.frontend.views.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.marketplace.business.services.UserService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.User;
import org.xine.marketplace.model.filters.UserFilter;

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

    /** The filter users Criteria. */
    private UserFilter filter;

    /** The selected user. */
    private User selectedUser;

    // -------------------------------------------------------------------------
    //
    // Constructors and it Callbacks
    //
    // -------------------------------------------------------------------------

    /**
     * Inits the.
     */
    @PostConstruct
    private void init() {
	this.filter = new UserFilter();
    }

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

    /**
     * Gets the filter.
     *
     * @return the filter
     */
    public UserFilter getFilter() {
	return this.filter;
    }

    /**
     * Sets the filter.
     *
     * @param filter
     *            the new filter
     */
    public void setFilter(final UserFilter filter) {
	this.filter = filter;
    }

    /**
     * Gets the selected user.
     *
     * @return the selected user
     */
    public User getSelectedUser() {
	return this.selectedUser;
    }

    /**
     * Sets the selected user.
     *
     * @param selectedUser
     *            the new selected user
     */
    public void setSelectedUser(final User selectedUser) {
	this.selectedUser = selectedUser;
    }

    // -------------------------------------------------------------------------
    //
    // Methods Handlers
    //
    // -------------------------------------------------------------------------

    /**
     * Search.
     */
    public void search() {
	this.users = this.service.search(this.filter);
    }

    /**
     * Delete.
     */
    public void delete() {
	this.service.delete(this.selectedUser);
	this.users.remove(this.selectedUser);
	FacesUtil.addInfoMessage("User '" + this.selectedUser.getUsername()
		+ "-" + this.selectedUser.getEmail() + "' deleted.");
	this.selectedUser = null;
    }
}
