package eu.paulharris.controller;

import eu.paulharris.domain.Recipe;
import eu.paulharris.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(path = "/recipes/query")
    ResponseEntity<List<Recipe>> queryRecipe() {
        return ResponseEntity.ok(recipeService.queryRecipe());
    }
}
