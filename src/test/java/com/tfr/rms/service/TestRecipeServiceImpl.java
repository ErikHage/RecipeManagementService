package com.tfr.rms.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.tfr.rms.dao.RecipeRepository;
import com.tfr.rms.helper.RecipeTestHelper;
import com.tfr.rms.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Erik on 9/5/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRecipeServiceImpl {

    @Mock
    private RecipeRepository mockRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertRecipe() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");

        when(mockRepository.insert(rec1)).thenReturn(rec1);

        Recipe recOut = recipeService.insertRecipe(rec1);
        assertEquals(rec1, recOut);

        verify(mockRepository, times(1)).insert(rec1);
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testFindRecipeById() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");
        when(mockRepository.findOne("test1")).thenReturn(rec1);

        Recipe result = recipeService.findRecipeById("test1");
        assertEquals(rec1, result);

        verify(mockRepository, times(1)).findOne("test1");
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testFindRecipeByName() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");
        when(mockRepository.findByName("Test Recipe 1")).thenReturn(Collections.singletonList(rec1));

        List<Recipe> results = recipeService.findRecipesByName("Test Recipe 1");
        assertEquals(1, results.size());
        assertEquals(rec1, results.get(0));

        verify(mockRepository, times(1)).findByName("Test Recipe 1");
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testFindAll() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");
        Recipe rec2 = RecipeTestHelper.getTestRecipe("Test Recipe 2");
        rec2.setId("test2");

        when(mockRepository.findAll()).thenReturn(Arrays.asList(rec1,rec2));

        List<Recipe> results = recipeService.findAll();
        assertEquals(2, results.size());
        assertEquals(rec1, results.get(0));
        assertEquals(rec2, results.get(1));

        verify(mockRepository, times(1)).findAll();
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testUpdateRecipe() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");

        when(mockRepository.save(rec1)).thenReturn(rec1);

        Recipe recOut = recipeService.updateRecipe(rec1);
        assertEquals(rec1, recOut);

        verify(mockRepository, times(1)).save(rec1);
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testDeleteRecipe() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");

        recipeService.deleteRecipe(rec1);

        verify(mockRepository, times(1)).delete(rec1);
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testGetRecipeNames() {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.setId("test1");
        Recipe rec2 = RecipeTestHelper.getTestRecipe("Test Recipe 2");
        rec2.setId("test2");
        Recipe rec3 = RecipeTestHelper.getTestRecipe("Test Recipe 3");
        rec3.setId("test3");

        List<Recipe> recipes = Arrays.asList(rec1, rec2, rec3);

        when(mockRepository.findAll()).thenReturn(recipes);

        Map<String,String> names = recipeService.getRecipeNames();

        assertEquals(3, names.size());
        assertEquals("Test Recipe 1", names.get("test1"));
        assertEquals("Test Recipe 2", names.get("test2"));
        assertEquals("Test Recipe 3", names.get("test3"));

        verify(mockRepository, times(1)).findAll();
        verifyNoMoreInteractions(mockRepository);
    }

}
