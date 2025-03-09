package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long id;
    private String name;
    private String location;

    public PetStoreData(PetStore petStore) {
        this.id = petStore.getId();
        this.name = petStore.getName();
        this.location = petStore.getLocation();
    }
}
