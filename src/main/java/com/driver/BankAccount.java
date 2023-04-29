package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.balance = balance;
        this.name = name;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if (digits * 9 < sum) {
            // If the maximum possible sum of digits is less than the required sum,
            // it is not possible to generate an account number
            throw new Exception("Account Number can not be generated");
        }

        StringBuilder accountNumberBuilder = new StringBuilder();
        int remainingSum = sum;

        // Generate the remaining digits
        for (int i = 0; i < digits; i++) {
            int maxDigit = Math.min(remainingSum, 9);
            int digit = (int) (Math.random() * (maxDigit + 1));
            accountNumberBuilder.append(digit);
            remainingSum -= digit;
        }

        String accountNumber = accountNumberBuilder.toString();

        if (remainingSum != 0) {
            // If there is a remaining sum after generating the digits,
            // it means it was not possible to generate an account number
            throw new Exception("Account Number can not be generated");
        }

        return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if (this.balance - amount < 0 || this.balance - amount < this.minBalance) {
            throw new Exception("Insufficient Balance");
        }

        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}