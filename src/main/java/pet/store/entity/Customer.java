package pet.store.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerEmail;
    private String customerName;

    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    private Set<PetStore> petStores;

	public void setName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	public void setContactInfo(Object contactInfo) {
		// TODO Auto-generated method stub
		
	}
}

