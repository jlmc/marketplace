package org.xine.marketplace.frontend.views.exer;

import org.xine.marketplace.frontend.views.exer.model.Contract;
import org.xine.marketplace.frontend.views.exer.repository.ContractRepository;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The Class CapFourExerciseBean.
 */
// @javax.faces.view.ViewScoped
// @ViewScoped
// @SessionScoped
// @ManagedBean
@Named
@ViewScoped
public class CapFourExerciseBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The repository. */
    @Inject
    private ContractRepository repository;
    // = ContractRepository.getInstance();

    /** The contracts. */
    private List<Contract> contracts;

    private Contract contract;

    @PostConstruct
    private void init() {
        System.out.println("CapFourExerciseBean on @PostConstruct");

        clean();
    }

    private void clean() {
        setContract(new Contract());

        this.contracts = this.repository.getContracts();
    }

    /**
     * Save.
     */
    public void save() {
        System.out.println("Contract -> " + this.contract);

        this.repository.addContract(this.contract);
        clean();
    }

    /**
     * Gets the contracts.
     * @return the contracts
     */
    public List<Contract> getContracts() {
        return this.contracts;
    }

    /**
     * Sets the contracts.
     * @param contracts
     *            the new contracts
     */
    public void setContracts(final List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(final Contract contract) {
        this.contract = contract;
    }

}
