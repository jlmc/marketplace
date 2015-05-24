/**
 *
 */
package org.xine.marketplace.frontend.views.controller.dialog;

import org.primefaces.context.RequestContext;
import org.xine.marketplace.business.services.clients.ClientService;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class SelectClientBean. Controller of the Client Selection Dialog view.
 * @author Joao Costa
 */
@Named
@ViewScoped
public class SelectClientBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The client service. */
    @Inject
    private ClientService clientService;

    /** The name. */
    private String name;

    /** The clients. */
    private List<Client> clients;

    /**
     * Search.
     */
    public void search() {
        this.clients = this.clientService.search(new ClientFilter(null, this.name));
    }

    /**
     * call opendialog method from the view when we want to open the SelectClientDialog from some
     * event view.
     */
    @SuppressWarnings({"boxing", "static-method" })
    public void openDialog() {

        final Map<String, Object> options = new HashMap<>();

        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentHeight", 470);
        options.put("contentWidth", 600);

        RequestContext.getCurrentInstance().openDialog("/dialog/SelectClient", options, null);

    }

    @SuppressWarnings("static-method")
    /**
     * Event to select the client record from teh view, will close the dialog
     * @param client - the selected client on the view
     */
    public void selectOne(final Client client) {
        RequestContext.getCurrentInstance().closeDialog(client);
    }

    /**
     * Gets the name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the clients.
     * @return the clients
     */
    public List<Client> getClients() {
        return this.clients;
    }

}
