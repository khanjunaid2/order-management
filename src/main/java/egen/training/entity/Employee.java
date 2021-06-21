package egen.training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.soap.Name;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findALl", query = "SELECT emp from Employee emp"),
//        @NamedQuery(name = "Employee.insert", query = )
})

public class Employee {
    @Id
    String id;
    String name;
    String email;

    public Employee(){
        this.id = UUID.randomUUID().toString();
    }
    public Employee(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", emial='" + email + '\'' +
                '}';
    }
}
