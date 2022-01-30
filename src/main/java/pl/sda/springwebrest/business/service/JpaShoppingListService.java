package pl.sda.springwebrest.business.service;

import org.springframework.stereotype.Service;
import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.ShoppingList;
import pl.sda.springwebrest.data.repository.ItemRepository;
import pl.sda.springwebrest.data.repository.ShoppingListRepository;

import java.util.List;
import java.util.Optional;

@Service("jpaService")
public class JpaShoppingListService implements ShoppingListService{
    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;

    public JpaShoppingListService(ShoppingListRepository shoppingListRepository, ItemRepository itemRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }

    @Override
    public Optional<ShoppingList> findById(long id) {
        return shoppingListRepository.findById(id);
    }

    @Override
    public ShoppingList save(ShoppingListDto dto) {
        return null;
    }
}
