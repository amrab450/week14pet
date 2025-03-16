package pet.store.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import pet.store.controller.model.PetStoreData;

@Data
@Entity
@Table(name = "pet_store")
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petStoreId;

    private String petStoreName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "pet_store_customer",
               joinColumns = @JoinColumn(name = "pet_store_id"),
               inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAddress(Class<? extends PetStoreData> class1) {
		// TODO Auto-generated method stub
		
	}

	public void setName11(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setName1(String name) {
		// TODO Auto-generated method stub
		
	}

	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}
