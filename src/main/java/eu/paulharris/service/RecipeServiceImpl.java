package eu.paulharris.service;

import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.RecipeRepository;
import eu.paulharris.domain.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> queryRecipe(SearchCriteria searchCriteria) {
        List<Recipe> allRecipes = StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return allRecipes.stream().filter(filterPredicate(searchCriteria)).collect(Collectors.toList());
    }

    private static Predicate<Recipe> filterPredicate(SearchCriteria searchCriteria) {
        return recipe -> (searchCriteria.getVegetarian() == null || recipe.isVegetarian() == searchCriteria.getVegetarian()) &&
                (searchCriteria.getServings() == null || recipe.getServings() == searchCriteria.getServings()) &&
                (searchCriteria.getIncludeIngredients() == null || recipe.getIngredients().toLowerCase().contains(searchCriteria.getIncludeIngredients().toLowerCase())) &&
                (searchCriteria.getExcludeIngredients() == null || !recipe.getIngredients().toLowerCase().contains(searchCriteria.getExcludeIngredients().toLowerCase())) &&
                (searchCriteria.getIncludeInstructions() == null || recipe.getInstructions().toLowerCase().contains(searchCriteria.getIncludeInstructions().toLowerCase())) &&
                (searchCriteria.getExcludeInstructions() == null || !recipe.getInstructions().toLowerCase().contains(searchCriteria.getExcludeInstructions().toLowerCase()));
    }
}
