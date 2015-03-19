package org.xine.marketplace.frontend.views.exer.repository;

import org.xine.marketplace.frontend.views.exer.model.Contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContractRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    // private static AtomicReference<ContractRepository> instance = new AtomicReference<>();

    private final List<Contract> contracts = new ArrayList<>();

    private final AtomicLong sequence = new AtomicLong(0);

    public ContractRepository() {
        System.out.println("OLA sou ContractRepository");
    }

    // public final static ContractRepository getInstance() {
    // if (instance.get() == null) {
    // instance.set(new ContractRepository());
    // }
    // return instance.get();
    // }

    public List<Contract> getContracts() {
        return new ArrayList<>(this.contracts);
    }

    public Contract addContract(final Contract c) {

        if (c == null) {
            throw new IllegalArgumentException("the parameters can't nort be null");
        }

        c.setId(Long.valueOf(this.sequence.incrementAndGet()));
        this.contracts.add(c);

        return c;
    }

}
