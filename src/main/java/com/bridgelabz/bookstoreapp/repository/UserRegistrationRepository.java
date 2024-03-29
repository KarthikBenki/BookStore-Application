package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserData, Long> {

@Query(value = "select * from user_registration where email=:email",nativeQuery = true)
    public UserData findUserDataByEmail(String email);

    @Query(value = "select * from user_registration where email=:email",nativeQuery = true)
    public Optional<UserData> findUserDataByEmailId(String email);

}
