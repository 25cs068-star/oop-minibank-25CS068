import java.util.*;
import java.util.regex.Pattern;

class Validator {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("[6-9][0-9]{9}");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

    private static final Pattern PAN_PATTERN = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]");

    private static final Pattern IFSC_PATTERN = Pattern.compile("[A-Z]{4}0[A-Z0-9]{6}");

    public static boolean isValidMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPan(String pan) {
        return PAN_PATTERN.matcher(pan).matches();
    }

    public static boolean isValidIfsc(String ifsc) {
        return IFSC_PATTERN.matcher(ifsc).matches();
    }
}

enum TransactionType {
    DEPOSIT, WITHDRAW, TRANSFER
}

record Command(TransactionType type, String accountNumber, long amount) {
}

class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.split(" ");

        TransactionType type = TransactionType.valueOf(parts[0]);

        String accountNumber = parts[1];

        long amount = Long.parseLong(parts[2]);

        return new Command(type, accountNumber, amount);
    }
}

class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder statement = new StringBuilder();

        statement.append("----- Account Statement -----\n");
        statement.append("Account Number: ")
                .append(account.getAccountNumber())
                .append("\n");

        statement.append("Owner Name: ")
                .append(account.getOwnerName())
                .append("\n");

        statement.append("Balance: ")
                .append(account.getBalance())
                .append("\n");

        statement.append("Interest Rate: ")
                .append(account.interestRate())
                .append("%\n");

        statement.append("Active: ")
                .append(account.getActive())
                .append("\n");

        return statement.toString();
    }
}

class Customer implements Cloneable {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;

    private static long customerCounter = 101;

    private static String generateCustomerId() {
        return "CUST" + customerCounter++;
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

abstract class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 1;

    private static String generateAccountNumber() {
        return String.format("AC%04d", accountCounter++);
    }

    public Account(String ownerName, long balance) {
        accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        balance = balance + amount;
    }

    public boolean withdraw(long amount) {
        if (canWithdraw(amount)) {
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
                ", Owner Name: " + ownerName +
                ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Account))
            return false;

        Account other = (Account) o;

        return accountNumber.equals(other.accountNumber);
    }
}

class SavingsAccount extends Account {
    private long minBalance;

    public SavingsAccount(String ownerName, long balance, long minBalance) {
        super(ownerName, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return getBalance() - amount >= minBalance;
    }

    public long getMinBalance() {
        return minBalance;
    }
}

class CurrentAccount extends Account {
    private long overdraftLimit;

    public CurrentAccount(String ownerName, long balance, long overdraftLimit) {
        super(ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {
        return 0.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return getBalance() - amount >= -overdraftLimit;
    }

    public long getOverdraftLimit() {
        return overdraftLimit;
    }
}

class FixedDepositAccount extends Account {
    public FixedDepositAccount(String ownerName, long balance) {
        super(ownerName, balance);
    }

    @Override
    public double interestRate() {
        return 7.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return false;
    }
}

public class MiniBank {
    record BankInfo(String name, String branch) {
    }

    enum MenuOption {
        OPEN_ACCOUNT, DEPOSITE, WITHDRAW, TRANSFER, EXIT
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "Main Branch");

        Customer c1 = new Customer(
                "ABC",
                "ABC@gmail.com",
                "798XXXXXXX");

        Customer.Address address = new Customer.Address(
                "Main Road",
                "Anand",
                "388001");

        c1.setAddress(address);

        Account acc[] = new Account[3];

        acc[0] = new SavingsAccount(
                "ABC",
                5000,
                1000);

        acc[1] = new CurrentAccount(
                "XYZ",
                3000,
                2000);

        acc[2] = new FixedDepositAccount(
                "PQR",
                10000);

        System.out.println("----- Account Information -----");

        for (int i = 0; i < acc.length; i++) {
            System.out.println(acc[i].toString());
        }

        System.out.println();

        System.out.println("----- Interest Rate Test -----");

        for (int i = 0; i < acc.length; i++) {
            System.out.println(
                    acc[i].getAccountNumber()
                            + " Interest Rate: "
                            + acc[i].interestRate()
                            + "%");
        }

        System.out.println();

        System.out.println("----- Instanceof Test -----");

        if (acc[0] instanceof SavingsAccount) {
            System.out.println(
                    acc[0].getAccountNumber()
                            + " is a SavingsAccount");
        }

        System.out.println();

        System.out.println("----- Equals Test -----");

        System.out.println(
                "acc[0] equals acc[1]: "
                        + acc[0].equals(acc[1]));

        System.out.println();

        System.out.println("----- Customer Address -----");

        System.out.println(
                "Address: "
                        + c1.getAddress().getLine());

        System.out.println(
                "City: "
                        + c1.getAddress().getCity());

        System.out.println(
                "Pincode: "
                        + c1.getAddress().getPincode());

        System.out.println();

        Customer c2 = c1.clone();

        System.out.println(
                "Customer cloned successfully: "
                        + (c2 != null));

        System.out.println();

        System.out.println("----- Validator Test -----");

        System.out.println(
                "Valid Mobile: "
                        + Validator.isValidMobile("9876543210"));

        System.out.println(
                "Invalid Mobile: "
                        + Validator.isValidMobile("12345"));

        System.out.println(
                "Valid Email: "
                        + Validator.isValidEmail("abc@gmail.com"));

        System.out.println(
                "Invalid Email: "
                        + Validator.isValidEmail("abc@"));

        System.out.println(
                "Valid PAN: "
                        + Validator.isValidPan("ABCDE1234F"));

        System.out.println(
                "Invalid PAN: "
                        + Validator.isValidPan("ABC123"));

        System.out.println(
                "Valid IFSC: "
                        + Validator.isValidIfsc("SBIN0001234"));

        System.out.println(
                "Invalid IFSC: "
                        + Validator.isValidIfsc("SBIN123"));

        System.out.println();

        System.out.println("----- Command Parser Test -----");

        String line = "DEPOSIT AC0001 500";

        Command command = CommandParser.parse(line);

        System.out.println(
                "Type: " + command.type());

        System.out.println(
                "Account Number: "
                        + command.accountNumber());

        System.out.println(
                "Amount: "
                        + command.amount());

        System.out.println();

        System.out.println(
                StatementFormatter.buildStatement(acc[0]));

        while (true) {
            System.out.println(
                    "--------------------"
                            + bank.name()
                            + "--------------------");

            System.out.println("1. Open Account");
            System.out.println("2. Deposite");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter your choice : ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    System.out.println(
                            "Customer ID : "
                                    + c1.getCustomerId());

                    System.out.println(
                            "Customer Name : "
                                    + c1.getName());

                    for (int i = 0; i < acc.length; i++) {
                        System.out.println(
                                acc[i].toString());
                    }

                    break;

                case 2:

                    acc[0].deposit(1000);
                    acc[1].deposit(500);
                    acc[2].deposit(2000);

                    System.out.println(
                            "Deposit Successful");

                    for (int i = 0; i < acc.length; i++) {
                        System.out.println(
                                acc[i].getAccountNumber()
                                        + " Balance : "
                                        + acc[i].getBalance());
                    }

                    break;

                case 3:

                    if (acc[0].withdraw(2000))
                        System.out.println(
                                "Withdrawal Successful");
                    else
                        System.out.println(
                                "Insufficient Balance");

                    if (acc[1].withdraw(5000))
                        System.out.println(
                                "Withdrawal Successful");
                    else
                        System.out.println("Insufficient Balance");

                    if (acc[2].withdraw(500))
                        System.out.println("Withdrawal Successful");
                    else
                        System.out.println("Withdrawal Not Allowed");

                    for (int i = 0; i < acc.length; i++) {
                        System.out.println(acc[i].getAccountNumber() + " Balance : " + acc[i].getBalance());
                    }

                    break;

                case 4:

                    System.out.println("Transfer - to be implemented in next lab");

                    break;

                case 5:

                    System.out.println("Successfully exited from your account");

                    sc.close();
                    return;

                default:

                    System.out.println("Invalid choice!! Please try again.");
            }
        }
    }
}