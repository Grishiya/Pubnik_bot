package com.wik.stuff.W.K_Stuff_assist.service;

import com.wik.stuff.W.K_Stuff_assist.models.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Employee findEmployeeByChatId(Long chatId);

    void deleteEmployee(Long id);
}
