package org.xine.marketplace.business.services;

import org.xine.marketplace.business.BusinessException;
import org.xine.marketplace.model.entities.Client;
import org.xine.marketplace.model.filters.ClientFilter;
import org.xine.marketplace.repository.daos.ClientsRepository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * The Class ClientService.
 */
public class ClientService implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The client repository. */
    @Inject
    private ClientsRepository clientRepository;

    /**
     * Save.
     * @param client
     *            the client
     * @return the client
     */
    @Transactional
    public Client save(final Client client) {
        if (client.getId() == null) {
            // can't exists two clients with the same code or with the same Email
            final Set<Client> cls = new HashSet<>(this.clientRepository.shearch(client.getCnjp(),
                    client.getEmail()));
            if (!cls.isEmpty()) {
                throw new BusinessException(
                        "can't exists two clients with the same code or with the same Email");
            }
        }

        return this.clientRepository.save(client);
    }

    /**
     * Shearch.
     * @param filter
     *            the filter
     * @return the list
     */
    public List<Client> shearch(final ClientFilter filter) {
        return this.clientRepository.shearch(filter);
    }

    /**
     * Gets the by id.
     * @param id
     *            the id
     * @return the by id
     */
    public Client getById(final Long id) {
        return this.clientRepository.getById(id);
    }

    /**
     * Sets the client repository.
     * @param clientRepository
     *            the new client repository
     */
    public void setClientRepository(final ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

}
