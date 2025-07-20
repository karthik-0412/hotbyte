package pay;

public interface Payment {
	public double calBill(double amount);
	default double dis(double amount) {
		return amount*(amount - 0.05);
	}

}
