package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;
import br.com.zaek.loan.dto.LoanTypes;

import java.util.ArrayList;
import java.util.List;

public class Below3000Income implements CalculateLoan{
    @Override
    public List<Loan> calculate(Customer customer) {
        if (customer.getIncome() > 3000)
            throw new IllegalArgumentException("O salário informado é inválido neste contexto.");
        List<Loan> loans = new ArrayList<>();

        loans.add(new Loan(LoanTypes.PERSONAL, 4));

        if (customer.getAge() < 30 && customer.getLocation().equals("SP")) {
            loans.add(new Loan(LoanTypes.COLLATERALIZED, 3));
        }

        return loans;
    }
}
