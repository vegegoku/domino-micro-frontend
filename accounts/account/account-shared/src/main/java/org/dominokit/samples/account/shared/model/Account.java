package org.dominokit.samples.account.shared.model;

public class Account {

    private String name;
    private String accountNumber;
    private boolean active;

    public Account() {
    }

    public Account(String name, String accountNumber, boolean active) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
