package org.xine.marketplace.frontend.views.controller.clients;

import org.xine.marketplace.business.services.clients.ClientService;
import org.xine.marketplace.frontend.views.util.jsf.FacesUtil;
import org.xine.marketplace.model.entities.Address;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.entities.ClientType;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
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
    /** The service. */
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

    /** The editable address. */
    private Address editableAddress;

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------

    /**
     * Initialize.
     * @param e
     *            the e
     */
    public void initialize(final ComponentSystemEvent e) {
        if (FacesUtil.isNotPostback()) {
            if (this.client == null) {
                clean();
            }
        }
    }

    /**
     * Inits the.
     */
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
        if (this.client != null) {
            final boolean isEditOperation = this.client.getId() != null;
            this.client = this.service.save(this.client);
            FacesUtil.addInfoMessage("Client saved.");

            if (!isEditOperation) {
                clean();
            }
        }
    }

    /**
     * Adds the address.
     */
    public void saveAddress() {
        if (this.editableAddress != null && this.client != null) {
            this.editableAddress.setClient(this.client);
            if (!this.editableAddress.isEdit()) {
                this.client.getAddresses().add(this.editableAddress);
            }
            this.editableAddress = new Address();
        }
    }

    /**
     * Edits the address.
     */
    public void editAddress() {
        this.editableAddress.setClient(this.client);
        this.editableAddress = new Address();
    }

    /**
     * Removes the address.
     */
    public void removeAddress() {
        if (this.editableAddress != null && this.client != null) {
            this.client.getAddresses().remove(this.editableAddress);
            this.editableAddress = new Address();
        }
    }

    /**
     * Clean.
     */
    private void clean() {
        this.client = new Client();
        this.editableAddress = new Address();
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

    /**
     * Gets the editable address.
     * @return the editableAddress
     */
    public Address getEditableAddress() {
        return this.editableAddress;
    }

    /**
     * Sets the editable address.
     * @param editableAddress
     *            the editableAddress to set
     */
    public void setEditableAddress(final Address editableAddress) {
        this.editableAddress = editableAddress;
        this.editableAddress.setEdit(true);
    }

}
