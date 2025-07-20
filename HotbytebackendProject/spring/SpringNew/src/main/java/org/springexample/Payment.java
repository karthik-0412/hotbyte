package org.springexample;

public class Payment {
    int paymentId;
    String payment_sender;
    String payment_reciver;
    double amount;

    public Payment() {
    }

    public Payment(int paymentId, String payment_sender, String payment_reciver, double amount) {
        this.paymentId = paymentId;
        this.payment_sender = payment_sender;
        this.payment_reciver = payment_reciver;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayment_sender() {
        return payment_sender;
    }

    public void setPayment_sender(String payment_sender) {
        this.payment_sender = payment_sender;
    }

    public String getPayment_reciver() {
        return payment_reciver;
    }

    public void setPayment_reciver(String payment_reciver) {
        this.payment_reciver = payment_reciver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", payment_sender='" + payment_sender + '\'' +
                ", payment_reciver='" + payment_reciver + '\'' +
                ", amount=" + amount +
                '}';
    }
}
