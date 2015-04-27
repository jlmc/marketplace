package org.xine.marketplace.model.filters;

import org.xine.marketplace.model.entities.ClientType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class ClientFilter.
 */
public class ClientFilter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The code. */
    private String code;

    /** The name. */
    private String name;

    /** The types. */
    private Set<ClientType> types = new HashSet<>();

    /**
     * Instantiates a new client filter.
     */
    public ClientFilter() {
        super();
    }

    /**
     * Instantiates a new client filter.
     * @param code
     *            the code
     * @param name
     *            the name
     */
    public ClientFilter(final String code, final String name) {
        super();
        this.code = code;
        this.name = name;
    }

    /**
     * Gets the code.
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     * @param code
     *            the new code
     */
    public void setCode(final String code) {
        this.code = code;
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
     *            the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the types.
     * @return the types
     */
    public Set<ClientType> getTypes() {
        return this.types;
    }

    /**
     * Sets the types.
     * @param types
     *            the new types
     */
    public void setTypes(final Set<ClientType> types) {
        this.types = types;
    }

}
