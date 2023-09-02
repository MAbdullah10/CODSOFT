import java.util.Date;
class Transaction {
    private Date date;
    private String type;
    private double amount;
    public Transaction(String type, double amount) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return  "Date: " + date +
                "\nTransaction Type: " + type +
                "\nAmount: Rs " + amount + "\n";
    }
}