package com.wik.stuff.W.K_Stuff_assist.service;

import com.wik.stuff.W.K_Stuff_assist.models.Employee;
import com.wik.stuff.W.K_Stuff_assist.models.UserState;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface EmployeeService {
    void saveEmployee(Employee employee);




    @Cacheable(value = "employeeStateCache", key = "#chatId")
    Employee getEmployeeState(long chatId);

    @Cacheable(value = "userStateCache", key = "#chatId")
    UserState getUserState(long chatId);

    @CacheEvict(value = "userStateCache", key = "#chatId")
    void updateUserState(long chatId, UserState state);

    boolean existsManager();
}
