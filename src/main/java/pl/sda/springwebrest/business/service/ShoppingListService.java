package pl.sda.springwebrest.business.service;

import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListService {

    List<ShoppingList> findAll();

    Optional<ShoppingList> findById(long id);

    ShoppingList save(ShoppingListDto dto);
}
