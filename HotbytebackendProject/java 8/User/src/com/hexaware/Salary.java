package com.hexaware;

abstract class Employees {
    String name;
    double salary;
    double bon;

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getBon() {
        return bon;
    }

    abstract void calBon();

    public String toString() {
        return "Name: " + name + ", Salary: " + salary + ", Bonus: " + bon;
    }
}

class Temp extends Employees {
	@Override
    void calBon() {
        bon = salary * 5 / 100;
    }
}

class Per extends Employees {
	@Override
    void calBon() {
        bon = salary * 10 / 100;
    }
}

public class Salary {
    public static void main(String[] args) {
        Temp emp1 = new Temp();
        emp1.setName("Ram");
        emp1.setSalary(10000);
        emp1.calBon();
        System.out.println(emp1);

        Per emp2 = new Per();
        emp2.setName("Ravi");
        emp2.setSalary(20000);
        emp2.calBon();
        System.out.println(emp2);
    }
}
