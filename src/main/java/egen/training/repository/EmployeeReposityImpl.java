package egen.training.repository;

import egen.training.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.namespace.QName;
import java.util.List;

@Repository
public class EmployeeReposityImpl implements EmployeeRepository{

    @PersistenceContext
    private EntityManager em;

    public Employee insert(Employee employee){
        em.persist(employee);
        return employee;
    }
    public Employee getOne(String eid){
        Employee employee = em.find(Employee.class, eid);
        return employee;
    }
    public List<Employee> getAll(){
        TypedQuery<Employee> query = em.createNamedQuery("Employee.findALl", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

}
