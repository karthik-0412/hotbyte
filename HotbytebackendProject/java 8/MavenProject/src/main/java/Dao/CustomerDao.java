package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Customer;
import util.ConnectionHelper;

public class CustomerDao implements CustomerDao1 {

	static Connection con;

	PreparedStatement stat;

	static {

		try {

			con = ConnectionHelper.getConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@Override
	public void addCustomer(Customer c) {

		try {

			String sql = "insert into customer values(?,?,?,?)";

			stat = con.prepareStatement(sql);

			stat.setInt(1, c.getAcctno());
			stat.setString(2, c.getName());

			stat.setInt(3, c.getBalance());

			stat.setString(4, c.getTypeT());

			stat.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void showAll() {

		try {
			String sql = "select * from customer";
			stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("acctno") + "  ");
				System.out.print(rs.getString("name") + " ");
				System.out.print(rs.getInt("balance") + " ");
				System.out.print(rs.getString("typeT") + " ");

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public void searchByact(int ac) {

		try {
			String sql = "select * from customer where acctno=?";
			stat = con.prepareStatement(sql);
			stat.setInt(1, ac);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				System.out.print(rs.getInt("acctno") + "  ");
				System.out.print(rs.getString("name"));
				System.out.print(rs.getInt("balance"));

				System.out.print(rs.getString("typeT"));

			} else {
				System.out.print("Not Found");

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void searchByName(String name) {

		try {
			String sql = "select * from customer where name = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, name);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("acctno"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("balance"));
				System.out.println(rs.getString("typeT"));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	@Override
    public void RemoveByact(int ac) {

        try
        {
            String sql="delete  from customer where acctno=?";
            stat=con.prepareStatement(sql);
            stat.setInt(1, ac);
            int c= stat.executeUpdate();

            if(c==0)
            {
                System.out.println("Not Found");
            }

            else
            {
                System.out.println("Removed");

            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());


        }


    }
	@Override
    public void updateByact(int ac,int bal) {

        try
        {
            String sql="update customer set balance =? where acctno=?";
            stat=con.prepareStatement(sql);
            stat.setInt(1, bal);
            stat.setInt(2,ac);
            int c= stat.executeUpdate();

            if(c==0)
            {
                System.out.println("Not updated");
            }

            else
            {
                System.out.println("updated");

            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());


        }


    }

}
