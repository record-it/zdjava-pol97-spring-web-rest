package pl.sda.springwebrest.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.Item;
import pl.sda.springwebrest.data.entity.ShoppingList;
import pl.sda.springwebrest.data.mapper.ShoppingListMapper;
import pl.sda.springwebrest.data.repository.ItemRepository;
import pl.sda.springwebrest.data.repository.ShoppingListRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Transactional
    public ShoppingList save(ShoppingListDto dto) {
        final ShoppingList shoppingList = ShoppingListMapper.mapToEntity(dto);
        final Set<Item> items = shoppingList.getItems().stream()
                .peek(item -> item.setId(0))
                .map(itemRepository::save).collect(Collectors.toSet());
        shoppingList.setItems(items);
        return shoppingListRepository.save(shoppingList);
    }

    @Override
    public boolean delete(long id) {
        shoppingListRepository.deleteById(id);
        return true;
    }

    //TODO zaimplementuj
    @Override
    public ShoppingList update(ShoppingListDto dto, long id) {
        return null;
    }
}
