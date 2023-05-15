package eu.paulharris.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int numberOfServings;
    private boolean vegetarian;
    private String ingredients;
    private String instructions;

    public Recipe(String name,
                  int numberOfServings,
                  boolean vegetarian,
                  String ingredients,
                  String instructions) {
        this.name = name;
        this.numberOfServings = numberOfServings;
        this.vegetarian = vegetarian;
        this.ingredients = ingredients;
        this.instructions = instructions;

    }
}
