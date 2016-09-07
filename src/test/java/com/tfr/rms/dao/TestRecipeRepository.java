package com.tfr.rms.dao;

import com.tfr.rms.helper.RecipeTestHelper;
import com.tfr.rms.model.Ingredient;
import com.tfr.rms.model.Recipe;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * Created by Erik on 9/3/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRecipeRepository {

    private static final Logger logger = LoggerFactory.getLogger(TestRecipeRepository.class);

    @Autowired
    private RecipeRepository repository;

    @BeforeClass
    public static void setUpClass() {
        logger.debug("Running Recipe Repository Tests");
    }

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @After
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void testSaveRecipe() {
        logger.debug("testSaveRecipe");
        Recipe test1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");

        Recipe test1Out = repository.insert(test1);
        logger.debug(test1Out.toString());

        assertEquals(test1, test1Out);
    }

    @Test
    public void testFindOneRecipeByName() {
        logger.debug("testFindOneRecipeByName");
        Recipe test1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        repository.insert(test1);
        List<Recipe> test1Read = repository.findByName("Test Recipe 1");
        assertEquals(test1, test1Read.get(0));
    }

    @Test
    public void testUpdateRecipe() {
        logger.debug("testUpdateRecipe");
        Recipe test1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        repository.insert(test1);
        test1.name = "Test Recipe Updated";
        repository.save(test1);
        List<Recipe> test1Read = repository.findByName("Test Recipe Updated");
        assertEquals(test1, test1Read.get(0));
    }

    @Test
    public void testDeleteRecipe() {
        logger.debug("testDeleteRecipe");
        Recipe test1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        repository.insert(test1);
        List<Recipe> test1Read = repository.findByName("Test Recipe 1");
        assertEquals(test1, test1Read.get(0));
        repository.delete(test1Read);
        List<Recipe> test1Read2 = repository.findByName("Test Recipe 1");
        assertEquals(0, test1Read2.size());
    }

}
