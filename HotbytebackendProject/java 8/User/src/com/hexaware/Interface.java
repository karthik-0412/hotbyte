package com.hexaware;

interface Sim {
    void calling(int calls);
    void message(int messages);
    void calBill();
}

class Vodaphone implements Sim {
    int totalCalls = 0;
    int totalMessages = 0;
    double bill = 0;
    @Override
    public void calling(int calls) {
        totalCalls += calls;
        System.out.println("Vodaphone calls: " + calls);
    }
    @Override
    public void message(int messages) {
        totalMessages += messages;
        System.out.println("Vodaphone messages: " + messages);
    }
    @Override
    public void calBill() {
        bill = (totalCalls * 5.0) + (totalMessages * 1.5);
        System.out.println("Vodaphone total bill: ₹" + bill);
    }
}

class Airtel implements Sim {
    int totalCalls = 0;
    int totalMessages = 0;
    double bill = 0;
    @Override
    public void calling(int calls) {
        totalCalls += calls;
        System.out.println("Airtel calls: " + calls);
    }
    @Override
    public void message(int messages) {
        totalMessages += messages;
        System.out.println("Airtel messages: " + messages);
    }
    @Override
    public void calBill() {
        bill = (totalCalls * 5.0) + (totalMessages * 1.5);
        System.out.println("Airtel total bill: ₹" + bill);
    }
}

public class Interface {
    public static void main(String[] args) {
        Sim sim1 = new Vodaphone();
        sim1.calling(1);
        sim1.message(2);
        sim1.calBill();  

        Sim sim2 = new Airtel();
        sim2.calling(2);
        sim2.message(4);
        sim2.calBill();  
    }
}
