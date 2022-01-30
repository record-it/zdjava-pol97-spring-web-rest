package pl.sda.springwebrest.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.springwebrest.data.entity.Item;

@RepositoryRestResource(path = "/api/items", collectionResourceRel = "items")
public interface RestItemRepository extends PagingAndSortingRepository<Item, Long> {
}
