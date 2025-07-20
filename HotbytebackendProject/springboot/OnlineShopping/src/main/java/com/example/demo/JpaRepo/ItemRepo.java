package com.example.demo.JpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;

public interface ItemRepo extends JpaRepository<Item, String>{
//	@Transactional
//	@Modifying
//	@Query(value="update Item set quantity = quantity - :quan where code =:code", nativeQuery = true)
//	public int UpdateQuant(@Param("quan") int quan, @Param("code") String code);
	@Modifying
	@Transactional
	@Query("UPDATE Item i SET i.quantity = i.quantity - :quan WHERE i.code = :code")
	int UpdateQuant(@Param("quan") int quan, @Param("code") String code);
}
