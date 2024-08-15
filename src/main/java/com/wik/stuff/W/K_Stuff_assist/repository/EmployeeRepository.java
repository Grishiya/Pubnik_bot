package com.wik.stuff.W.K_Stuff_assist.repository;

import com.wik.stuff.W.K_Stuff_assist.models.Position;
import com.wik.stuff.W.K_Stuff_assist.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByChatId(Long chatId);

    boolean existsByPosition(Position position);
}
