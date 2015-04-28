package org.xine.marketplace.frontend.views.controller.clients;

import org.xine.marketplace.business.services.ClientService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.ClientType;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class ClientSaverBean.
 */
@Named
@ViewScoped
public class ClientSaverBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    //
    // business services
    //
    // -------------------------------------------------------------------------
    @Inject
    private ClientService service;
    // -------------------------------------------------------------------------
    //
    // Model properties
    //
    // -------------------------------------------------------------------------
    /** The client. */
    private Client client;

    /** The client types. */
    private final ClientType[] clientTypes = ClientType.values();

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------

    /**
     * Initialize.
     */
    public void initialize() {
        if (FacesUtil.isNotPostback()) {
            if (this.client == null) {
                clean();
            }
        }
    }

    @PostConstruct
    private void init() {
        clean();
    }

    // --------------------------------------------------------------------------
    //
    // Event handlers
    //
    // --------------------------------------------------------------------------
    /**
     * Save.
     */
    public void save() {
        this.service.save(this.client);
        FacesUtil.addInfoMessage("Client saved.");
        clean();
    }

    /**
     * Adds the address.
     */
    public void addAddress() {
        // TODO::
    }

    /**
     * Removes the address.
     */
    public void removeAddress() {
        // TODO::
    }

    /**
     * Clean.
     */
    private void clean() {
        this.client = new Client();
    }

    // --------------------------------------------------------------------------
    //
    // Getters and Setter
    //
    // --------------------------------------------------------------------------
    /**
     * Checks if is edits the.
     * @return true, if is edits the
     */
    public boolean isEdit() {
        return (this.client != null && this.client.getId() != null);
    }

    /**
     * Gets the client.
     * @return the client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Sets the client.
     * @param client
     *            the new client
     */
    public void setClient(final Client client) {
        this.client = client;
    }

    /**
     * Gets the client types.
     * @return the client types
     */
    public ClientType[] getClientTypes() {
        return this.clientTypes;
    }

}
