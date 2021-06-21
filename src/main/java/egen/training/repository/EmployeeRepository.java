package egen.training.repository;

import egen.training.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    public Employee insert(Employee employee);

    List<Employee> getAll();

    Employee getOne(String eid);
}
