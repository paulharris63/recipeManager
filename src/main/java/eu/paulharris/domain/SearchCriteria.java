package eu.paulharris.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchCriteria {
    private Boolean vegetarian;
    private Integer servings;
    private String includeIngredients;
    private String excludeIngredients;
    private String includeInstructions;
    private String excludeInstructions;
}
