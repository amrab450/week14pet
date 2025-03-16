package pet.store.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

    private final PetStoreService petStoreService;

    public PetStoreController(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @PutMapping("/{petStoreId}")
    @ResponseStatus(HttpStatus.OK)
    public PetStoreData updatePetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreData petStoreData) {
        petStoreData.setId(petStoreId);
        log.info("Updating pet store with ID: {}", petStoreId);
        return petStoreService.savePetStore(petStoreData);
    }

    @GetMapping("/{petStoreId}")
    @ResponseStatus(HttpStatus.OK)
    public PetStoreData getPetStore(@PathVariable Long petStoreId) {
        log.info("Retrieving pet store with ID: {}", petStoreId);
        return petStoreService.findPetStoreById(petStoreId);
    }

    @DeleteMapping("/{petStoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, String> deletePetStore(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID: {}", petStoreId);
        petStoreService.findPetStoreById(petStoreId);
        return Collections.singletonMap("message", "Pet store deleted successfully");
    }

    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
        log.info("Adding employee to pet store {}", petStoreId);
        return petStoreService.saveEmployee(petStoreId, employee);
    }

    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
        log.info("Adding customer to pet store {}", petStoreId);
        return petStoreService.saveCustomer(petStoreId, customer);
    }

    @GetMapping
    public List<PetStoreData> getAllPetStores() {
        log.info("Retrieving all pet stores");
        return petStoreService.retrieveAllPetStores();
    }
}


