package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long petStoreId;
    private String name;
    private String address;
    private Set<PetStoreCustomer> customers = new HashSet<>();
	private Object employees;
  

    // Constructor to map PetStore entity to PetStoreData DTO
    @SuppressWarnings("unchecked")
	public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.name = petStore.getName();
        this.address = petStore.getAddress();

        // Map customers
        for (Customer customer : petStore.getCustomers()) {
            this.customers.add(new PetStoreCustomer());
        }

        // Map employees
        for (Employee employee : petStore.getEmployees()) {
            ((Set<PetStoreCustomer>) this.employees).add(new PetStoreEmployee(employee));
        }
    }


	public void setId(Long petStoreId2) {
		// TODO Auto-generated method stub
		
	}
}
