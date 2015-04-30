package org.xine.marketplace.validator.constraints;

public class ZipCodeStubModel {

    private String zipCode;

    private String name;

    /**
     * @return the zipCode
     */
    @ZipCode
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
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

}
