package egen.training.controller;

import egen.training.entity.Employee;
import egen.training.repository.EmployeeRepository;
import egen.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    // Get all the Empployees
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAll() {
        List<Employee> employees = employeeService.getAll();
        return employees;
    }

    // Get One Employee
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Employee getOne(@PathVariable("id") String eid) {
        Employee employee = employeeService.getOne(eid);
        return employee;
    }

    //Create new employee
    @RequestMapping(method = RequestMethod.POST)
    public Employee insertEmployee(@RequestBody Employee employee) {
        employeeService.insert(employee);
        return employee;
    }
}
