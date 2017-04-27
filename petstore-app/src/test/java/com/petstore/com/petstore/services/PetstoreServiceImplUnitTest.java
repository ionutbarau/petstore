package com.petstore.com.petstore.services;

import com.petstore.entities.Pet;
import com.petstore.repositories.CategoryRepository;
import com.petstore.repositories.PetRepository;
import com.petstore.repositories.TagRepository;
import com.petstore.services.PetServiceImpl;
import com.petstore.validators.PetValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit test for {@link PetServiceImpl}.
 *
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 27/04/2017.
 * Time: 02:08
 */
@RunWith(MockitoJUnitRunner.class)
public class PetstoreServiceImplUnitTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private PetValidator petValidator;

    @InjectMocks
    private PetServiceImpl petServiceImpl =  new PetServiceImpl();

    private List<Pet> pets;

    @Before
    public void setUp(){
        pets = new ArrayList<>();
        pets.add(new Pet());
    }

    @Test
    public void testList(){
        when(petRepository.findAll()).thenReturn(pets);
        List<Pet> result = petServiceImpl.list();
        verify(petRepository, times(1)).findAll();
        assertEquals("Result pet list has wrong size !", pets.size(), result.size());
    }

    @Test
    public void testFind(){
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("myPet");
        when(petRepository.findOne(1)).thenReturn(pet);
        Pet result = petServiceImpl.find(1);
        verify(petRepository, times(1)).findOne(1);
        assertEquals("Result pet is not the same !", pet, result);
    }


    //TODO other tests here

}
