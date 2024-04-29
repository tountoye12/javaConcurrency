package org.diallo.concurrency;

import org.diallo.concurrency.application.SimpleAccount;

public class SimpleAccountDemo {
    public static void main(String[] args) {

        var account1 = new SimpleAccount();
        var account2 = new SimpleAccount();

        int repeat = 1000;

        // Thread to handle transfers from account1 to account2
        var t1 = Thread.ofPlatform().unstarted(
                () -> {
                    for (int i = 0; i < repeat; i++) {
                        account1.transfer(account2, 100);
                    }
                }
        );
        // Thread to handle transfers from account2 to account1
        var t2 = Thread.ofPlatform().unstarted(
                () -> {
                    for (int i = 0; i < repeat; i++) {
                        account2.transfer(account1, 100);
                    }
                }
        );


        // start both thread
        t1.start();
        t2.start();

        // Print out the final balance of each account
        System.out.println("account1.getBalance() = " + account1.getBalance());
        System.out.println("account2.getBalance() = " + account2.getBalance());

    }
}