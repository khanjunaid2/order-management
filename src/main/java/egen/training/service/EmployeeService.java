package egen.training.service;

import egen.training.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee insert(Employee employee);

    List<Employee> getAll();

    Employee getOne(String eid);
}
