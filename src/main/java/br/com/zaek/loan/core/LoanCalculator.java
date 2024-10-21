package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;
import br.com.zaek.loan.dto.Output;

import java.util.ArrayList;
import java.util.List;

public class LoanCalculator {
    private final Customer customer;

    public LoanCalculator(Customer customer) {
        this.customer = customer;
    }

    public Output calculate() {
        if (customer.getIncome() == null) throw new IllegalArgumentException("O salário do cliente não pode ser nulo.");

        List<Loan> loans = new ArrayList<Loan>();

        if (customer.getIncome() < 3000) {
            loans = new Below3000Income().calculate(customer);
        } else if (customer.getIncome() >= 3000 && customer.getIncome() <= 5000) {
            loans = new Between3000And5000Income().calculate(customer);
        } else {
            loans = new GreaterThan5000Income().calculate(customer);
        }

        return new Output(customer.getName(), loans);
    }
}
