package eu.paulharris.service;

import eu.paulharris.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Override
    public List<Recipe> queryRecipe() {
        return Collections.singletonList(new Recipe("Recipe name", 4, false, "ingredients", "instructions"));
    }
}
