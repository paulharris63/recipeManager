package eu.paulharris;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesBackendApplicationIT {

    private static final String RECIPE_1_NAME = "Spaghetti Bolognese";
    private static final boolean RECIPE_1_IS_VEGETARIAN = false;
    private static final int RECIPE_1_NUMBER_OF_SERVINGS = 4;
    private static final String RECIPE_1_INGREDIENTS = "tomatoes, meat, basil";
    private static final String RECIPE_1_INSTRUCTIONS = "Cook everything in a pan";
    private static final String RECIPE_1_UPDATED_NAME = RECIPE_1_NAME + " Royale";
    private static final String LINE_ENDING = System.getProperty("line.separator");
    public static final String RECIPE_1_EXPECTED_CONTENT = "{" + LINE_ENDING +
            "  \"name\" : \"" + RECIPE_1_NAME + "\"," + LINE_ENDING +
            "  \"servings\" : " + RECIPE_1_NUMBER_OF_SERVINGS + "," + LINE_ENDING +
            "  \"vegetarian\" : " + RECIPE_1_IS_VEGETARIAN + "," + LINE_ENDING +
            "  \"ingredients\" : \"" + RECIPE_1_INGREDIENTS + "\"," + LINE_ENDING +
            "  \"instructions\" : \"" + RECIPE_1_INSTRUCTIONS + "\"," + LINE_ENDING +
            "  \"_links\" : {" + LINE_ENDING +
            "    \"self\" : {" + LINE_ENDING +
            "      \"href\" : \"http://localhost/recipes/1\"" + LINE_ENDING +
            "    }," + LINE_ENDING +
            "    \"recipe\" : {" + LINE_ENDING +
            "      \"href\" : \"http://localhost/recipes/1\"" + LINE_ENDING +
            "    }" + LINE_ENDING +
            "  }" + LINE_ENDING +
            "}";
    public static final String RECIPE_1_EXPECTED_FLAT_CONTENT = "[{" +
            "\"id\":1," +
            "\"name\":\"" + RECIPE_1_NAME + "\"," +
            "\"servings\":" + RECIPE_1_NUMBER_OF_SERVINGS + "," +
            "\"vegetarian\":" + RECIPE_1_IS_VEGETARIAN + "," +
            "\"ingredients\":\"" + RECIPE_1_INGREDIENTS + "\"," +
            "\"instructions\":\"" + RECIPE_1_INSTRUCTIONS + "\"}]";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void testAllCRUDoperations() throws Exception {
        addFirstRecipe();
        getFirstRecipe();
        queryFirstRecipe();
        updateFirstRecipe();
        deleteFirstRecipe();
        confirmDatabaseEmpty();
    }

    private void confirmDatabaseEmpty() {
        List<Recipe> recipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toList());
        assertEquals(0, recipes.size());
    }

    private void deleteFirstRecipe() throws Exception {
        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isNoContent());
    }

    private void updateFirstRecipe() throws Exception {
        Recipe recipeToUpdate = new Recipe(RECIPE_1_UPDATED_NAME, RECIPE_1_NUMBER_OF_SERVINGS, RECIPE_1_IS_VEGETARIAN, RECIPE_1_INGREDIENTS, RECIPE_1_INSTRUCTIONS);
        mockMvc.perform(put("/recipes/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recipeToUpdate)))
                .andExpect(status().isNoContent());
        List<Recipe> recipes = recipeRepository.findByName(RECIPE_1_NAME);
        assertEquals(0, recipes.size());
        recipes = recipeRepository.findByName(RECIPE_1_UPDATED_NAME);
        assertEquals(1, recipes.size());
        assertEquals(RECIPE_1_NUMBER_OF_SERVINGS, recipes.get(0).getServings());
        assertEquals(RECIPE_1_IS_VEGETARIAN, recipes.get(0).isVegetarian());
        assertEquals(RECIPE_1_INGREDIENTS, recipes.get(0).getIngredients());
        assertEquals(RECIPE_1_INSTRUCTIONS, recipes.get(0).getInstructions());
    }

    private void getFirstRecipe() throws Exception {
        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(RECIPE_1_EXPECTED_CONTENT));
    }

    private void queryFirstRecipe() throws Exception {
        mockMvc.perform(get("/recipes/query?servings=4"))
                .andExpect(status().isOk())
                .andExpect(content().string(RECIPE_1_EXPECTED_FLAT_CONTENT));
    }

    private void addFirstRecipe() throws Exception {
        Recipe recipeToAdd = new Recipe(RECIPE_1_NAME, RECIPE_1_NUMBER_OF_SERVINGS, RECIPE_1_IS_VEGETARIAN, RECIPE_1_INGREDIENTS, RECIPE_1_INSTRUCTIONS);
        mockMvc.perform(post("/recipes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(recipeToAdd)))
                .andExpect(status().isCreated());

        List<Recipe> recipes = recipeRepository.findByName(RECIPE_1_NAME);
        assertEquals(1, recipes.size());
        assertEquals(RECIPE_1_NUMBER_OF_SERVINGS, recipes.get(0).getServings());
        assertEquals(RECIPE_1_IS_VEGETARIAN, recipes.get(0).isVegetarian());
        assertEquals(RECIPE_1_INGREDIENTS, recipes.get(0).getIngredients());
        assertEquals(RECIPE_1_INSTRUCTIONS, recipes.get(0).getInstructions());
    }
}
