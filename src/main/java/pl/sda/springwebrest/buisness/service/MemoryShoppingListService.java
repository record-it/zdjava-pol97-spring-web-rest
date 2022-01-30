package pl.sda.springwebrest.buisness.service;

import org.springframework.stereotype.Service;
import pl.sda.springwebrest.data.entity.ShoppingList;

import java.time.LocalDate;
import java.util.*;

@Service
public class MemoryShoppingListService implements ShoppingListService{
    private Map<Long, ShoppingList> lists = new HashMap<>();

    public MemoryShoppingListService() {
        lists.put(1L, ShoppingList.builder()
                .id(1L)
                        .name("Moja lista")
                        .created(LocalDate.now())
                        .owner("Karol")

                .build()
        );
        
    }

    @Override
    public List<ShoppingList> findAll() {
        return new ArrayList<>(lists.values());
    }

    @Override
    public Optional<ShoppingList> findById(long id) {
        return Optional.ofNullable(lists.get(id));
    }
}
