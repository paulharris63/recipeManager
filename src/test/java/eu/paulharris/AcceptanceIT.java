package eu.paulharris;

import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceIT {
    private static final String QUERY_1_EXPECTED_CONTENT = "[{\"id\":1,\"name\":\"Vegetarian recipe\",\"servings\":2,\"vegetarian\":true,\"ingredients\":\"Vegetables\",\"instructions\":\"Peel and cook in pan\"}]";
    private static final String QUERY_2_EXPECTED_CONTENT = "[{\"id\":2,\"name\":\"Meaty recipe\",\"servings\":4,\"vegetarian\":false,\"ingredients\":\"Meat, Potatoes\",\"instructions\":\"Put in Oven\"}]";
    private static final String QUERY_3_EXPECTED_CONTENT = "[{\"id\":2,\"name\":\"Meaty recipe\",\"servings\":4,\"vegetarian\":false,\"ingredients\":\"Meat, Potatoes\",\"instructions\":\"Put in Oven\"}]";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void acceptanceTest() throws Exception {
        Recipe recipe1 = new Recipe("Vegetarian recipe", 2, true, "Vegetables", "Peel and cook in pan");
        recipeRepository.save(recipe1);
        Recipe recipe2 = new Recipe("Meaty recipe", 4, false, "Meat, Potatoes", "Put in Oven");
        recipeRepository.save(recipe2);
        mockMvc.perform(get("/recipes/query").param("vegetarian", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(QUERY_1_EXPECTED_CONTENT));
        mockMvc.perform(get("/recipes/query")
                        .param("servings", "4")
                        .param("include-ingredients", "potatoes"))
                .andExpect(status().isOk())
                .andExpect(content().string(QUERY_2_EXPECTED_CONTENT));
        mockMvc.perform(get("/recipes/query")
                        .param("exclude-ingredients", "salmon")
                        .param("include-instructions", "oven"))
                .andExpect(status().isOk())
                .andExpect(content().string(QUERY_3_EXPECTED_CONTENT));

    }
}
