package pet.store.controller.model;

import pet.store.entity.Employee;

public class PetStoreCustomer {
    private Long customerId;
    private String firstName;
    private String lastName;
    private Long petStoreId;

    // Getters and setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPetStoreId() {
        return petStoreId;
    }

	public Object getContactInfo() {
		// TODO Auto-generated method stub
		return null;
	}

   
}
public class PetStoreEmployee {
    private Long employeeId;
    private String firstName;
    private String lastName;

    public PetStoreEmployee(Employee employee) {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public Object getRole() {
		// TODO Auto-generated method stub
		return null;
	}
}
