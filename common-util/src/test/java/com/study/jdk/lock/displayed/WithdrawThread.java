package com.study.jdk.lock.displayed;

import com.study.jdk.lock.Account;

/**
 * <p></p>
 *
 * @author wangzhj
 * @time 2016-11-28 10:58
 */
public class WithdrawThread implements Runnable {

    private Account account =  null;

    public WithdrawThread(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
            account.withdraw(1);
        }
    }
}