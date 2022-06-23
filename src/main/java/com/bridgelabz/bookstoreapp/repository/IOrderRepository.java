package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.OrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetailsModel, Long> {

}
