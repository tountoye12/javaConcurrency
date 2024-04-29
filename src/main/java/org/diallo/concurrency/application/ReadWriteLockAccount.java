package org.diallo.concurrency.application;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockAccount {

    private double balance;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public boolean withdraw(double amount) {
        rwLock.writeLock().lock();
        try {
            balance -= amount;
            return true;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public boolean deposit(double amount) {
        rwLock.writeLock().lock();
        try {
            balance += amount;
            return true;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public boolean transfer(ReadWriteLockAccount to, double amount) {
        rwLock.writeLock().lock();
        try {
            if (!this.withdraw(amount)) return false; // Withdrawal failure
            return to.deposit(amount); // Deposit and return the result
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public double getBalance() {
        rwLock.readLock().lock();
        try {
            return balance;
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
