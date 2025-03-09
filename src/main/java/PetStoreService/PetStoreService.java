package PetStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

import java.util.NoSuchElementException;

@Service
public class PetStoreService {

    @Autowired
    private PetStoreDao petStoreDao;

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getId());
        copyPetStoreFields(petStore, petStoreData);
        petStore = petStoreDao.save(petStore);
        return new PetStoreData(petStore);
    }

    public PetStoreData findPetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store with ID " + petStoreId + " not found"));
        return new PetStoreData(petStore);
    }

    public void deletePetStoreById(Long petStoreId) {
        if (!petStoreDao.existsById(petStoreId)) {
            throw new NoSuchElementException("Pet store with ID " + petStoreId + " not found");
        }
        petStoreDao.deleteById(petStoreId);
    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId == null) {
            return new PetStore();
        }
        return petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store with ID " + petStoreId + " not found"));
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setName(petStoreData.getName());
        petStore.setLocation(petStoreData.getLocation());
    }
}
