package org.diallo.concurrency.application;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleAccount {


    private double balance = 0;

    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        balance -= amount;
    }

    public void transfer(SimpleAccount to,double amount) {
        withdraw(amount);
        to.deposit(amount);
    }
}
