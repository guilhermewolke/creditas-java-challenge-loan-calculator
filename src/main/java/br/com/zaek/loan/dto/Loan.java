package br.com.zaek.loan.dto;

public record Loan(LoanTypes type, Integer tax) {
    public String toString() {
        return "[type: " + type + " | tax: " + tax + "]";
    }
}
