package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegistrationRepository extends JpaRepository<UserData, Long> {

}
