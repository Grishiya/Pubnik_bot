package com.wik.stuff.W.K_Stuff_assist.repository;

import com.wik.stuff.W.K_Stuff_assist.Role;
import com.wik.stuff.W.K_Stuff_assist.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByChatId(Long chatId);

    boolean existsByRole(Role role);
}
