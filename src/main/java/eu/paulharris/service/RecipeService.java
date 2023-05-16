package eu.paulharris.service;

import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.SearchCriteria;

import java.util.List;

public interface RecipeService {
    List<Recipe> queryRecipe(SearchCriteria searchCriteria);
}
