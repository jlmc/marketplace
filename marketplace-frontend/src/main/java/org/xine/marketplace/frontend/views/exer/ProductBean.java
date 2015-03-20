package org.xine.marketplace.frontend.views.exer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.xine.marketplace.model.entities.Product;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Product> products;

    private Product product;

    private static final AtomicLong SEC = new AtomicLong(0);

    private Product selectedProduct;

    @SuppressWarnings("boxing")
    public void add() {
        if (this.product != null) {

            final FacesContext contex = FacesContext.getCurrentInstance();

            this.product.setId(SEC.incrementAndGet());

            this.products.add(this.product);

            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "sucesso",
                    "Produto " + this.product.getSku() + "Adicionado");

            contex.addMessage("sucessAlert", msg);
        }

        clean();
    }

    public void delete() {
        if (this.selectedProduct != null) {
            this.products.remove(this.selectedProduct);
            clean();
        }
    }

    private void clean() {
        this.product = new Product();
        this.selectedProduct = null;
    }

    @PostConstruct
    private void init() {
        this.products = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final Product p = new Product("sku " + i, "name " + i);
            p.setId(Long.valueOf(SEC.incrementAndGet()));
            this.products.add(p);
        }

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

    public Product getSelectedProduct() {
        return this.selectedProduct;
    }

    public void setSelectedProduct(final Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

}
