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

    public String log() {
        return String.format("Vegetarian : %s, Servings : %d, Include ingredients : %s, Exclude ingredients : %s, Include instructions : %s, Exclude instructions : %s",
                vegetarian, servings, includeIngredients, excludeIngredients, includeInstructions, excludeInstructions);
    }
}
