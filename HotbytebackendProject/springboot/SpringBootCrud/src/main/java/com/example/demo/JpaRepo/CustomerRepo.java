package com.example.demo.JpaRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
//	@Query("select c from Customer c")
	@Query(value = "select * from customer",nativeQuery = true)
	  List<Customer> findAllCustomer();
//	@Query("select c from Customer c where c.actno = :actno")
	@Query(value ="select * from Customer  where actno = :actno",nativeQuery = true)
		public Customer findByActJPQL(@Param("actno") int actno);
//	@Query("select c from Customer c where c.name = :name")
	@Query(value ="select * from customer where name =:name",nativeQuery = true)
	public Customer findByNameJPQL(@Param("name") String name);
	@Transactional
	@Modifying
//	@Query("update Customer  c set c.balance=c.balance+ :amt  where c.actno=:actno")
	@Query(value = "update Customer c set c.balance=c.balance+ :amt  where c.actno=:actno",nativeQuery = true )
    public int deposit1(@Param("actno") int actno, @Param("amt")double amt);
	@Transactional
	@Modifying
//	@Query("update Customer  c set c.balance=c.balance- :amt  where c.actno=:actno")
	@Query(value ="update Customer  c set c.balance=c.balance- :amt  where c.actno=:actno",nativeQuery = true)
	public int withdraw(@Param("actno") int actno, @Param("amt")double amt);
	
	@Transactional
	@Modifying
//	@Query("delete from Customer c where  c.actno = :actno")
	@Query(value = "delete from Customer c where  c.actno = :actno",nativeQuery = true)
	public int deleteCustomer(@Param("actno") int actno);

}
