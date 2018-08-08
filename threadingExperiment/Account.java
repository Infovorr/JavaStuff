/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a generic account for messenger users, and acts as a lock for
 * anyone trying to access the database as a particular user.
 * @author Val
 */
public class Account {
    
    private final int number;                           //Account number
    private boolean notInUse = true;                    //Is the account in use
    private final Lock useLock = new ReentrantLock();   //Lock on the account
    
    public Account(int number) {
        this.number = number;
    }
    
    //Attempt to acquire and lock the account for use
    public boolean getAccount() {
        this.notInUse = useLock.tryLock();
        return this.notInUse;
    }
    
    //Release and unlock the account when it's no longer being used
    public void releaseAccount() {
        if (this.notInUse) {
            useLock.unlock();
            this.notInUse = true;
        }
    }
    
    //Return the account's ID number
    public int getNum() {
        return this.number;
    }
}
