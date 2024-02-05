import java.util.ArrayList;
import java.util.List;

class Bank {
    private String name;
    private List<Account> accounts;
    private List<ATM> atms;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
        this.atms = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setBank(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setBank(null);
    }

    public void addATM(ATM atm) {
        atms.add(atm);
        atm.setBank(this);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<ATM> getATMs() {
        return atms;
    }

    public String getName() {
        return name;
    }
}

class Account {
    private String accountNumber;
    private String holderName;
    private double balance;
    private Bank bank;

    public Account(String accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = 0;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void replenishAccount(double amount) {
        balance += amount;
    }

    public void withdrawFromAccount(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public Bank getBank() {
        return bank;
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private String location;
    private Bank bank;

    public ATM(String location) {
        this.location = location;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void withdrawMoneyFromAccount(String pinCode, double amount) {
        for (Account account : bank.getAccounts()) {
            if (account.getBank() == bank) {
                account.withdrawFromAccount(amount);
                System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        Bank myBank = new Bank("MyBank");

        Account account1 = new Account("123456", "John Doe");
        Account account2 = new Account("789012", "Jane Doe");

        myBank.addAccount(account1);
        myBank.addAccount(account2);

        ATM atm1 = new ATM("ATM1");
        ATM atm2 = new ATM("ATM2");
        ATM atm3 = new ATM("ATM3");

        myBank.addATM(atm1);
        myBank.addATM(atm2);
        myBank.addATM(atm3);

        // Display account status
        System.out.println("Account 1 Status:");
        System.out.println("Balance: " + account1.getBalance());

        // Withdraw from account
        account1.withdrawFromAccount(50);

        // Display updated account status
        System.out.println("Updated Account 1 Status:");
        System.out.println("Balance: " + account1.getBalance());

        // Use ATM to withdraw money
        atm1.withdrawMoneyFromAccount("1234", 30);
    }
}
