package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.controller.model.PetStoreData;
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
    public void deletePetStore(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID: {}", petStoreId);
        petStoreService.findPetStoreById(petStoreId);
    }
}
