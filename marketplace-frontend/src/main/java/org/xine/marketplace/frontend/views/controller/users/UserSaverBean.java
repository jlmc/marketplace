package org.xine.marketplace.frontend.views.controller.users;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.marketplace.business.services.UserService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Permission;
import org.xine.marketplace.model.entities.User;

/**
 * The Class UserSaverBean.
 */
@Named
@ViewScoped
public class UserSaverBean implements Serializable {

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
    // Model properties
    //
    // -------------------------------------------------------------------------

    /** The user. */
    private User user;

    /** The all permissions. */
    private List<Permission> allPermissions;

    /**
     * The selected permission. The selected Permission to add to the user
     * permissions.
     */
    private Permission selectedPermission;

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------
    /**
     * Initialize. the single operations
     */
    @PostConstruct
    private void initialize() {
	System.out.println("->initialize @PostConstruct");
	this.allPermissions = this.service.getPermissions();
	if (this.user == null) {
	    clean();
	}
    }

    public void init() {
	if (FacesUtil.isNotPostback()) {
	    if (this.user == null) {
		clean();
	    }
	}
    }

    /**
     * Clean.
     */
    private void clean() {
	this.user = new User();
    }

    // --------------------------------------------------------------------------
    //
    // Event handlers
    //
    // --------------------------------------------------------------------------
    /**
     * Adds the permission.
     */
    public void addPermission() {
	if (this.selectedPermission != null) {
	    this.user.getPermissions().add(this.selectedPermission);
	    this.selectedPermission = null;
	}
    }

    /**
     * Save operation.
     */
    public void save() {
	this.user = this.service.save(this.user);

	FacesUtil.addInfoMessage("User created with sucess.");
	clean();
    }

    /**
     * Checks if is edits the.
     *
     * @return true, if is edits the
     */
    public boolean isEdit() {
	return this.user != null && this.user.getId() != null;
    }

    // --------------------------------------------------------------------------
    //
    // Bean Properties - Getters and Setters
    //
    // --------------------------------------------------------------------------

    /**
     * Gets the all permissions.
     *
     * @return the all permissions
     */
    public List<Permission> getAllPermissions() {
	return this.allPermissions;
    }

    public User getUser() {
	return this.user;
    }

    public void setUser(final User user) {
	this.user = user;
    }

    /**
     * Gets the selected permission.
     *
     * @return the selected permission
     */
    public Permission getSelectedPermission() {
	return this.selectedPermission;
    }

    /**
     * Sets the selected permission.
     *
     * @param selectedPermission
     *            the new selected permission
     */
    public void setSelectedPermission(final Permission selectedPermission) {
	this.selectedPermission = selectedPermission;
    }

}
