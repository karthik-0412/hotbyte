package model;

public class Customer {
 
	
	
	int acctno;
	String name;
	int balance;
	String typeT;
	
	public Customer()
	{
		
	}
 
	public Customer(int acctno, String name, int balance, String typeT) {
		super();
		this.acctno = acctno;
		this.name = name;
		this.balance = balance;
		this.typeT = typeT;
	}
 
	public int getAcctno() {
		return acctno;
	}
 
	public void setAcctno(int acctno) {
		this.acctno = acctno;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public int getBalance() {
		return balance;
	}
 
	public void setBalance(int balance) {
		this.balance = balance;
	}
 
	public String getTypeT() {
		return typeT;
	}
 
	public void setTypeT(String typeT) {
		this.typeT = typeT;
	}
 
	@Override
	public String toString() {
		return "customer [acctno=" + acctno + ", name=" + name + ", balance=" + balance + ", typeT=" + typeT + "]";
	}
	
	
	
	
}