package eu.paulharris.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
}
