package org.diallo.concurrency.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SynchronizedAccount {


    private double balance = 0;

    public synchronized void deposit(double amount) {

        balance += amount;
    }

    public synchronized void withdraw(double amount) {

        balance -= amount;
    }

    public synchronized void transfer(SynchronizedAccount to, double amount) {
        withdraw(amount);
        to.deposit(amount);
    }
}
