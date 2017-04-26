package com.petstore.runners;

import com.petstore.entities.Category;
import com.petstore.entities.Pet;
import com.petstore.entities.Tag;
import com.petstore.entities.User;
import com.petstore.repositories.CategoryRepository;
import com.petstore.repositories.PetRepository;
import com.petstore.repositories.TagRepository;
import com.petstore.repositories.UserRepository;
import com.petstore.security.PetStoreSecurityRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Ionut Barau (ionutbarau)
 * Project: petstore
 * Date: 23/04/2017.
 * Time: 21:43
 */
@Component
public class DbRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DbRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... strings) throws Exception {
        logger.debug("Executing DBRunner...");
        //CREATE DEFAULT USERS
        logger.debug("Trying to create default users...");
        User guest = new User();
        guest.setUsername("guest");
        //this should be encrypted
        guest.setPassword("password");
        guest.setRole(PetStoreSecurityRoles.GUEST);

        User manager = new User();
        manager.setUsername("manager");
        //this should be encrypted
        manager.setPassword("password");
        manager.setRole(PetStoreSecurityRoles.MANAGER);

        userRepository.save(guest);
        userRepository.save(manager);
        logger.debug("Default users created successfully !");

        //CREATE DEFAULT PETS
        logger.debug("Trying to create default pets with tags and categories...");
        //Create the bear
        Tag cute = tagRepository.save(new Tag(null, "cute"));
        Tag fluffy = tagRepository.save(new Tag(null, "fluffy"));
        Tag playful = tagRepository.save(new Tag(null, "playful"));

        Set<Tag> bearTags = new HashSet<>();
        bearTags.add(cute);
        bearTags.add(fluffy);

        Category bearCategory = categoryRepository.save(new Category(null, "BEAR"));
        Set<String> bearPhotoUrls = new HashSet<>();
        bearPhotoUrls.add("http://cdn.abclocal.go.com/content/ktrk/images/cms/1536907_1280x720.jpg");
        bearPhotoUrls.add("https://pbs.twimg.com/media/C4lh_ncWYAELchm.jpg");

        Pet bear = new Pet(null, bearCategory, "Barney the Bear", bearPhotoUrls, bearTags, "available");

        //Create the crocodile
        Set<Tag> crocodileTags = new HashSet<>();
        crocodileTags.add(cute);
        crocodileTags.add(playful);

        Category crocodileCategory = categoryRepository.save(new Category(null, "CROCODILE"));
        Set<String> crocodilePhotoUrls = new HashSet<>();
        crocodilePhotoUrls.add("http://i.dailymail.co.uk/i/pix/2016/04/05/12/048A50DF0000044D-0-image-a-45_1459855888116.jpg");
        crocodilePhotoUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Bazoule_sacred_crocodiles_MS_6709cropped.JPG/1200px-Bazoule_sacred_crocodiles_MS_6709cropped.JPG");

        Pet crocodile = new Pet(null, crocodileCategory, "Ben the Croc", crocodilePhotoUrls, crocodileTags, "available");

        petRepository.save(bear);
        petRepository.save(crocodile);
        logger.debug("Default pets with tags and categories created successfully !");
        logger.debug("DBRunner executed successfully !");
    }
}
