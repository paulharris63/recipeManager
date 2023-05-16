package eu.paulharris.controller;

import eu.paulharris.domain.Recipe;
import eu.paulharris.domain.SearchCriteria;
import eu.paulharris.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(path = "/recipes/query")
    ResponseEntity<List<Recipe>> queryRecipe(@RequestParam(required = false) Boolean vegetarian,
                                             @RequestParam(required = false) Integer servings,
                                             @RequestParam(required = false, name = "include-ingredients") String includeIngredients,
                                             @RequestParam(required = false, name = "exclude-ingredients") String excludeIngredients,
                                             @RequestParam(required = false, name = "include-instructions") String includeInstructions,
                                             @RequestParam(required = false, name = "exclude-instructions") String excludeInstructions) {
        log.debug("Vegetarian           : {}", vegetarian);
        log.debug("Servings             : {}", servings);
        log.debug("Include ingredients  : {}", includeIngredients);
        log.debug("Exclude ingredients  : {}", excludeIngredients);
        log.debug("Include instructions : {}", includeInstructions);
        log.debug("Exclude instructions : {}", excludeInstructions);
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .vegetarian(vegetarian)
                .servings(servings)
                .includeIngredients(includeIngredients)
                .excludeIngredients(excludeIngredients)
                .includeInstructions(includeInstructions)
                .excludeInstructions(excludeInstructions).build();
        return ResponseEntity.ok(recipeService.queryRecipe(searchCriteria));
    }
}
