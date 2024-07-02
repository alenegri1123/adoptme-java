package com.adoptme.petshop.services;

import com.adoptme.petshop.entities.Pet;
import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.repositories.PetsRepository;
import com.adoptme.petshop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetsService {
    @Autowired private PetsRepository repository;
    @Autowired private UsersRepository usersRepository;

    public Pet save(Pet pet) {
        return repository.save(pet);
    }
    public List<Pet> read() {
        return repository.findAll();
    }

    public Optional<Pet> readOne(Long id) {
        return repository.findById(id);
    }

    public void deleteOne(Long id) {
        repository.deleteById(id);
    }

    //metodo para adoptar una mascota
    public Pet adoptPet(Long petId, Long userId) throws Exception {
        // Buscar la mascota
        Optional<Pet> pet = repository.findById(petId);
        // el metodo findById del repositorio JPA devuelve siempre un opcional de algo..
        // la mascota puede existir o no
        // la variable pet es de tipo "Optional Pet" que NO es lo mismo que Pet
        if (!pet.isPresent()) {
            throw new Exception("PET NOT FOUND");
        }
        Optional<User> user = usersRepository.findById(userId);
        if (!user.isPresent()) {
            throw new Exception("USER NOT FOUND");
        }

        //Luego de encontrar la mascota y el usuario, obtengo los correspondientes datos con GET()
        Pet foundPet = pet.get();
        // para transformar u obtener de la mascota Opcional, la mascota encontrada
        // voy a utilizar el metodo get()
        User foundUser = user.get();
        //igual q Pet, guardamos en "foundUser" el dato encontrado.

        //ahora SETEO la propiedad
        foundPet.setOwner(foundUser);
        //tengo que guardar ahora
        return repository.save(foundPet); //mascota modificada, no es nueva PUT

        //faltaria hacer la logica si NO encontramos dato.


    }

}
