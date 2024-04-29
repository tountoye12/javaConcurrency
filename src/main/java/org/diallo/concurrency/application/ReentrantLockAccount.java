package org.diallo.concurrency.application;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAccount {

    private double balance = 0;

    private final Lock lock = new ReentrantLock();


    public void withdraw(double amount) {
        lock.lock();
        try {
            this.balance -= amount;
        }
        finally {
            lock.unlock();
        }
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }


    public void transfer( ReentrantLockAccount to,double amount) {

        lock.lock();
        try {
            withdraw(amount);
            to.deposit(amount);
        }
        finally {
            lock.unlock();
        }
    }
}
