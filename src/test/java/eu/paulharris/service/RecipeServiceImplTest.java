package eu.paulharris.service;

import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.RecipeRepository;
import eu.paulharris.domain.SearchCriteria;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

    private final RecipeRepository recipeRepository = mock(RecipeRepository.class);
    private final RecipeService recipeService = new RecipeServiceImpl(recipeRepository);

    @Test
    void queryVegetarianRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 4, true, "ingredients", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(true)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryVegetarianRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 4, false, "ingredients", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(true)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void queryServingsRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "ingredients", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(3)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryServingsRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 4, true, "ingredients", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(3)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void queryIncludeIngredientsRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients("meat")
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryIncludeIngredientsRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "fish, spices", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients("meat")
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void queryExcludeIngredientsRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients("vegetables")
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryExcludeIngredientsRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients("meat")
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void queryIncludeInstructionsRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "cook in oven")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions("oven")
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryIncludeInstructionsRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "cook in oven")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions("pan")
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void queryExcludeInstructionsRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "cook in oven")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions("pan")
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryExcludeInstructionsRecipeFail() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 3, true, "meat, spices", "cook in oven")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(null)
                .servings(null)
                .includeIngredients(null)
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions("oven")
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    void query4personsAndPotatoesRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 4, true, "Potatoes", "instructions")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(true)
                .servings(4)
                .includeIngredients("PotaToes")
                .excludeIngredients(null)
                .includeInstructions(null)
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    void queryNoSalmonAndOvenInstructionRecipe() {
        when(recipeRepository.findAll()).thenReturn(Collections.singletonList(new Recipe("Recipe name", 4, true, "Potatoes", "Oven")));
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(true)
                .servings(4)
                .includeIngredients(null)
                .excludeIngredients("Salmon")
                .includeInstructions("oven")
                .excludeInstructions(null)
                .build();
        List<Recipe> recipeList = recipeService.queryRecipe(searchCriteria);
        assertNotNull(recipeList);
        assertEquals(1, recipeList.size());
    }

}
