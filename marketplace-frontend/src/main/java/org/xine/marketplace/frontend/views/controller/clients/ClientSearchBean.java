package org.xine.marketplace.frontend.views.controller.clients;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.marketplace.business.services.ClientService;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;

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
    public void search() {
	this.clients = this.service.search(this.filter);
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
     *
     * @return the clients
     */
    public List<Client> getClients() {
	return this.clients;
    }

    /**
     * Gets the filter.
     *
     * @return the filter
     */
    public ClientFilter getFilter() {
	return this.filter;
    }

    /**
     * Sets the filter.
     *
     * @param filter
     *            the new filter
     */
    public void setFilter(final ClientFilter filter) {
	this.filter = filter;
    }

    /**
     * Sets the service.
     *
     * @param service
     *            the new service
     */
    public void setService(final ClientService service) {
	this.service = service;
    }
}
