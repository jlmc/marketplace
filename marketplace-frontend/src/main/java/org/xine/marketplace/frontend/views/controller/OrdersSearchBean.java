package org.xine.marketplace.frontend.views.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.xine.marketplace.frontend.model.Order;

@Named
@ViewScoped
public class OrdersSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Order> orders;

    /**
     * Inits the.
     */
    @PostConstruct
    private void init() {
        System.out.println("SearchOrdersBean PostConstruct");
        this.orders = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            this.orders.add(new Order(Long.valueOf(i + 1)));
        }
    }

    /**
     * Search.
     */
    @SuppressWarnings("static-method")
    public void search() {
        System.out.println("Search operation");
        System.out.println();

    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

}
