package pet.store.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeEmail;
    private String employeeName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_store_id")
    private PetStore petStore;

	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	public void setRole(Object role) {
		// TODO Auto-generated method stub
		
	}
}

