package pay;

public class Main {
	public static void main(String[] args) {
		double amount = 5000;
		Payment upi = (amount1) -> amount1 -= amount1 * 0.05;
		Payment cash = (amount1) -> amount1 -= amount1 * 0.1;
		Payment creditCard = (amount1) -> amount1 -= amount1 * 0.04;
		Payment other = (amount1) -> amount1 -= amount1 * 0.02;

		System.out.println(upi.calBill(amount));
		System.out.println(cash.calBill(amount));
		System.out.println(creditCard.calBill(amount));
		System.out.println(other.calBill(amount));
		System.out.println(upi.dis(upi.calBill(amount)));
		System.out.println(cash.calBill(cash.calBill(amount)));
		System.out.println(creditCard.dis(creditCard.calBill(amount)));
		System.out.println(other.dis(other.calBill(amount)));

	}
}
