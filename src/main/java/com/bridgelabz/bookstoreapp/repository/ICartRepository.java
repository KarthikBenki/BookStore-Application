package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.CartDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository  extends JpaRepository<CartDetailsModel,Long> {
}
