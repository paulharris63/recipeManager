package eu.paulharris.service;

import eu.paulharris.domain.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeServiceImplTest {

    private final RecipeService recipeService = new RecipeServiceImpl();

    @Test
    void queryRecipe() {
        List<Recipe> recipeList = recipeService.queryRecipe();
        assertEquals(1, recipeList.size());
    }
}
