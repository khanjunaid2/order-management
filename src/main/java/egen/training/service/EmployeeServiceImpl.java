package egen.training.service;

import egen.training.entity.Employee;
import egen.training.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee insert(Employee employee) {
        employeeRepository.insert(employee);
        return employee;
    }

    @Transactional
    public Employee getOne(String eid){
        Employee employee = employeeRepository.getOne(eid);
        return employee;
    }

    @Transactional
    public List<Employee> getAll(){
        List<Employee> employees = employeeRepository.getAll();
        if(employees.size()==0){
            return null;
        }
        return employees;
    }
}
