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

    /** The service. */
    @Inject
    private UserService service;

    /******************/

    /** The user. */
    private User user;

    /** The permissions. */
    private List<Permission> permissions;

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        // TODO:: may load the groups or permissions
        // nothing
        System.out.println("\n-----------------------------------------------");
        System.out.println("initialize @PostConstruct");
        System.out.println("-------------------------------------------------");

        this.permissions = this.service.getPermissions();
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
     * Clean.
     */
    private void clean() {
        this.user = new User();
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
