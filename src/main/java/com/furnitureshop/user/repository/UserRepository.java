package com.furnitureshop.user.repository;

import com.furnitureshop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.activeFlag <> 'D' AND u.role IS NOT NULL AND u.role.name <> 'ADMIN'")
    List<User> findUsers();

    @Query("SELECT u FROM User u WHERE u.activeFlag <> 'D' AND u.role IS NULL")
    List<User> findCustomers();

    int countByUsername(String username);

    int countByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void deleteByUsername(String username);
}