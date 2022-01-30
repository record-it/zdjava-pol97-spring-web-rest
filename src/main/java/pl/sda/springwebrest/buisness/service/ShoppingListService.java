package pl.sda.springwebrest.buisness.service;

import pl.sda.springwebrest.data.entity.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListService {

    List<ShoppingList> findAll();

    Optional<ShoppingList> findById(long id);
}
