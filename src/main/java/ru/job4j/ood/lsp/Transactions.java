package ru.job4j.ood.lsp;

public class Transactions {
    protected int moneyAmount;
    protected Account account;

    public Transactions(int moneyAmount, Account account) {
        this.moneyAmount = moneyAmount;
        this.account = account;
    }

    public void operation(int amount) {
        moneyAmount -= amount;
        int oldAmount = account.getMoneyAmount();
        account.setMoneyAmount(account.getMoneyAmount() + moneyAmount);
        rollback(amount, oldAmount);
    }

    protected void rollback(int amount, int oldAmount) {
        if (account.getMoneyAmount() != amount + oldAmount) {
            account.setMoneyAmount(oldAmount);
            moneyAmount += amount;
        }
    }
}

class Account {
    private int moneyAmount;

    public Account(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}

class WrongTransaction extends Transactions  {

    public WrongTransaction(int moneyAmount, Account account) {
        super(moneyAmount, account);
    }

    @Override
    protected void rollback(int amount, int oldAmount) {
        if (account.getMoneyAmount() != amount) {
            account.setMoneyAmount(oldAmount);
            moneyAmount += amount;
        }
    }
}
