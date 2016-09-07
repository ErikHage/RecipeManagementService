package com.tfr.rms.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.tfr.rms.config.Constants;
import com.tfr.rms.config.Routes;
import com.tfr.rms.helper.RecipeTestHelper;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;
import com.tfr.rms.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Erik on 9/5/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRecipeController {

    @Mock
    private RecipeService mockRecipeService;

    @InjectMocks
    private RecipeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testCreateRecipe_GivenRecipe_ExpectSuccess() throws Exception {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.id = "Test1";
        String json = RecipeTestHelper.recipeToJson(rec1);

        when(mockRecipeService.insertRecipe(rec1)).thenReturn(rec1);

        mockMvc.perform(post(Routes.RECIPE_CREATE)
            .contentType(Constants.APPLICATION_JSON)
            .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(Constants.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Test Recipe 1"))
        );

        verify(mockRecipeService, times(1)).insertRecipe(rec1);
        verifyNoMoreInteractions(mockRecipeService);
    }

    @Test
    public void testFindRecipeByName_GivenName_ExpectRecipe() throws Exception {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.id = "Test1";

        when(mockRecipeService.findRecipeByName("Test Recipe 1")).thenReturn(Collections.singletonList(rec1));

        mockMvc.perform(get(Routes.RECIPE_READ_BY_NAME.replace("{recipeName}", "Test Recipe 1")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(Constants.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1))
                );

        verify(mockRecipeService, times(1)).findRecipeByName("Test Recipe 1");
        verifyNoMoreInteractions(mockRecipeService);
    }

    @Test
    public void testFindAll_GivenRoute_ExpectListOfRecipes() throws Exception {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.id = "Test1";
        Recipe rec2 = RecipeTestHelper.getTestRecipe("Test Recipe 2");
        rec1.id = "Test2";
        Recipe rec3 = RecipeTestHelper.getTestRecipe("Test Recipe 3");
        rec1.id = "Test3";

        when(mockRecipeService.findAll()).thenReturn(Arrays.asList(rec1, rec2, rec3));

        mockMvc.perform(get(Routes.RECIPE_READ_ALL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(Constants.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3))
                );

        verify(mockRecipeService, times(1)).findAll();
        verifyNoMoreInteractions(mockRecipeService);
    }

    @Test
    public void testUpdateRecipe_GivenRecipe_ExpectSuccess() throws Exception {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.id = "Test1";
        String json = RecipeTestHelper.recipeToJson(rec1);

        when(mockRecipeService.updateRecipe(rec1)).thenReturn(rec1);

        mockMvc.perform(post(Routes.RECIPE_UPDATE)
                .contentType(Constants.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(Constants.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Test Recipe 1"))
                );

        verify(mockRecipeService, times(1)).updateRecipe(rec1);
        verifyNoMoreInteractions(mockRecipeService);
    }

    @Test
    public void testDeleteRecipe_GivenRecipe_ExpectSuccess() throws Exception {
        Recipe rec1 = RecipeTestHelper.getTestRecipe("Test Recipe 1");
        rec1.id = "Test1";
        String json = RecipeTestHelper.recipeToJson(rec1);

        ResponseMessage expectedReturn = new ResponseMessage("Recipe deleted: " + rec1.toString());

        when(mockRecipeService.deleteRecipe(rec1)).thenReturn(expectedReturn);

        mockMvc.perform(post(Routes.RECIPE_DELETE)
                .contentType(Constants.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(Constants.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(expectedReturn.getMessage()))
                );

        verify(mockRecipeService, times(1)).deleteRecipe(rec1);
        verifyNoMoreInteractions(mockRecipeService);
    }

}
