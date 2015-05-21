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
 * The Class SelectClientBean.
 * @author xine
 */
@Named
@ViewScoped
public class SelectClientBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Inject
    private ClientService clientService;

    private String name;

    private List<Client> clients;

    public void search() {
        this.clients = this.clientService.search(new ClientFilter(null, this.name));
    }

    @SuppressWarnings({"boxing", "static-method" })
    public void openDialog() {

        final Map<String, Object> options = new HashMap<>();

        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("/dialog/SelectClient", options, null);

    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the clients
     */
    public List<Client> getClients() {
        return this.clients;
    }

}
