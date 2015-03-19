package org.xine.marketplace.frontend.views.exer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.xine.marketplace.frontend.model.Product;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Product> products;

    private Product product;

    public void add() {
        if (this.product != null) {

            final FacesContext contex = FacesContext.getCurrentInstance();
            this.products.add(this.product);

            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "sucesso",
                    "Produto " + this.product.getSku() + "Adicionado");

            contex.addMessage("sucessAlert", msg);
        }

        clean();
    }

    private void clean() {
        this.product = new Product();
    }

    @PostConstruct
    private void init() {
        this.products = new ArrayList<>();
        clean();
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

}
