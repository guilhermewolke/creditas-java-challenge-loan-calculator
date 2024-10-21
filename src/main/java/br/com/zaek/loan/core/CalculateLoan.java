package br.com.zaek.loan.core;

import br.com.zaek.loan.dto.Customer;
import br.com.zaek.loan.dto.Loan;

import java.util.List;

public interface CalculateLoan {
    List<Loan> calculate(Customer customer);
}
