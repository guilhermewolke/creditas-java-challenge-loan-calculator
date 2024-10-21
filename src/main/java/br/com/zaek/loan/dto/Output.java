package br.com.zaek.loan.dto;

import java.util.List;

public record Output(String customer, List<Loan> loans) {
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("customer: " + customer + " | ");
        sb.append("loans: [");
        loans.forEach(sb::append);
        sb.append("]");
        return sb.toString();
    }
}
