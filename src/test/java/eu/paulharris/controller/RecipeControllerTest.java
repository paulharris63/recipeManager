package eu.paulharris.controller;

import eu.paulharris.domain.Recipe;
import eu.paulharris.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeControllerTest {
    private final RecipeService recipeService = mock(RecipeService.class);
    private final RecipeController recipeController = new RecipeController(recipeService);
    private static final Recipe RECIPE = new Recipe("Recipe1", 4, false, "", "");

    @Test
    void queryRecipe() {
        List<Recipe> recipes = Collections.singletonList(RECIPE);
        when(recipeService.queryRecipe(any())).thenReturn(recipes);
        ResponseEntity<List<Recipe>> response = recipeController.queryRecipe(true, 4, "", "", "", "");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}
