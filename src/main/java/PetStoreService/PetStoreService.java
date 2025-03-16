package PetStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CustomerDao.java.CustomerDao;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PetStoreService {

    @Autowired
    private PetStoreDao petStoreDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CustomerDao customerDao;

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
        copyPetStoreFields(petStore, petStoreData);
        petStore = petStoreDao.save(petStore);
        return new PetStoreData(petStore);
    }

    @Transactional
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee employeeData) {
        PetStore petStore = findPetStoreById(petStoreId);
        Employee employee = employeeData.getEmployeeId() != null
                ? findEmployeeById(employeeData.getEmployeeId())
                : new Employee();
        copyEmployeeFields(employee, employeeData);
        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);
        employee = employeeDao.save(employee);
        return new PetStoreEmployee(employee);
    }

    @Transactional
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer customerData) {
        PetStore petStore = findPetStoreById(petStoreId);
        Customer customer = customerData.getCustomerId() != null
                ? findCustomerById(customerData.getCustomerId())
                : new Customer();
        copyCustomerFields(customer, customerData);
        customer.getPetStores().add(petStore);
        petStore.getCustomers().add(customer);
        customer = customerDao.save(customer);
        return new PetStoreCustomer();
    }

    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> petStoreDataList = new ArrayList<>();
        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData(petStore);
            petStoreData.setCustomers(null);
            petStoreData.setEmployees(null);
            petStoreDataList.add(petStoreData);
        }
        return petStoreDataList;
    }

    @Transactional
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        return new PetStoreData(petStore);
    }

    @Transactional
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        petStoreDao.delete(petStore);
    }

    // Private Helper Methods
    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
            return new PetStore();
        }
        return findPetStoreById(petStoreId);
    }

    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store with ID " + petStoreId + " not found"));
    }

    private Employee findEmployeeById(Long employeeId) {
        return employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee with ID " + employeeId + " not found"));
    }

    private Customer findCustomerById(Long customerId) {
        return customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found"));
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setName(petStoreData.getName());
        petStore.setLocation(petStoreData.getAddress());
        if (petStore.getCustomers() == null) {
            petStore.setCustomers(new HashSet<>());
        }
        if (petStore.getEmployees() == null) {
            petStore.setEmployees(new HashSet<>());
        }
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee employeeData) {
        // Assuming Employee has setName() and setRole() methods:
        employee.setName(employeeData.getFirstName());
        employee.setRole(employeeData.getRole());
    }

    

    private void copyCustomerFields(Customer customer, PetStoreCustomer customerData) {
        customer.setName(customerData.getFirstName());
        customer.setContactInfo(customerData.getContactInfo());
    }
}
