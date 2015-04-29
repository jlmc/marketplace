package org.xine.marketplace.frontend.views.controller.clients;

import org.xine.marketplace.business.services.ClientService;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class ClientSearchBean.
 */
@Named
@ViewScoped
public class ClientSearchBean implements Serializable {

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

    /** The clients. */
    private List<Client> clients;

    /** The filter. */
    private ClientFilter filter;

    /** The client to delete. */
    private Client clientToDelete;

    // -------------------------------------------------------------------------
    //
    // Single operation
    //
    // -------------------------------------------------------------------------
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
     * Search.
     */
    public void search() {
        this.clients = this.service.search(this.filter);
    }

    /**
     * Delete.
     */
    public void delete() {
        if (this.clientToDelete != null) {
            this.service.delete(this.clientToDelete);
        }
    }

    /**
     * Clean.
     */
    private void clean() {
        this.filter = new ClientFilter();
    }

    // --------------------------------------------------------------------------
    //
    // Getters and Setter
    //
    // --------------------------------------------------------------------------

    /**
     * Gets the clients.
     * @return the clients
     */
    public List<Client> getClients() {
        return this.clients;
    }

    /**
     * Gets the filter.
     * @return the filter
     */
    public ClientFilter getFilter() {
        return this.filter;
    }

    /**
     * Sets the filter.
     * @param filter
     *            the new filter
     */
    public void setFilter(final ClientFilter filter) {
        this.filter = filter;
    }

    /**
     * Gets the client to delete.
     * @return the client to delete
     */
    public Client getClientToDelete() {
        return this.clientToDelete;
    }

    /**
     * Sets the client to delete.
     * @param clientToDelete
     *            the new client to delete
     */
    public void setClientToDelete(final Client clientToDelete) {
        this.clientToDelete = clientToDelete;
    }

    /**
     * Sets the service.
     * @param service
     *            the new service
     */
    public void setService(final ClientService service) {
        this.service = service;
    }
}
