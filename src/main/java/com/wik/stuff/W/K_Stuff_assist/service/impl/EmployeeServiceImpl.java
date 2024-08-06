package com.wik.stuff.W.K_Stuff_assist.service.impl;

import com.wik.stuff.W.K_Stuff_assist.models.Employee;
import com.wik.stuff.W.K_Stuff_assist.repository.EmployeeRepository;
import com.wik.stuff.W.K_Stuff_assist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    @Cacheable("employee")
    public Employee findEmployeeByChatId(Long chatId) {
        return employeeRepository.findByChatId(chatId).orElse(null);
    }

    @Override
    @CachePut(value = "employee", key = "#employee.chatId")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @CacheEvict(value = "employee", allEntries = true)
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
