package pl.sda.springwebrest.business.service;

import org.springframework.stereotype.Service;
import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.Item;
import pl.sda.springwebrest.data.entity.ShoppingList;
import pl.sda.springwebrest.data.mapper.ShoppingListMapper;

import java.time.LocalDate;
import java.util.*;

@Service("memoryService")
public class MemoryShoppingListService implements ShoppingListService {
    private Map<Long, ShoppingList> lists = new HashMap<>();
    private long listId = 0;
    private long itemId = 0;

    private long nextListId() {
        return ++listId;
    }

    private long nextItemId() {
        return ++itemId;
    }

    public MemoryShoppingListService() {
        lists.put(nextListId(), ShoppingList.builder()
                .id(listId)
                .name("Moja lista")
                .created(LocalDate.now())
                .items(new HashSet<>())
                .owner("Karol")
                .build()
        );
        lists.get(listId).getItems().add(Item.builder()
                .id(nextItemId())
                .name("mleko 1 L tłuste")
                .build());
        lists.get(listId).getItems().add(Item.builder()
                .id(nextItemId())
                .name("bułki wieloziarniste, 10 sztuk")
                .build());
        lists.put(nextListId(), ShoppingList.builder()
                .id(listId)
                .name("zakupy")
                .created(LocalDate.now())
                .items(new HashSet<>())
                .owner("Ania")
                .build()
        );
        lists.get(listId).getItems().add(Item.builder()
                .id(nextItemId())
                .name("masło 200 g")
                .build());
        lists.get(listId).getItems().add(Item.builder()
                .id(nextItemId())
                .name("chleb razowy")
                .build());
    }

    @Override
    public List<ShoppingList> findAll() {
        return new ArrayList<>(lists.values());
    }

    @Override
    public Optional<ShoppingList> findById(long id) {
        return Optional.ofNullable(lists.get(id));
    }

    @Override
    public ShoppingList save(ShoppingListDto dto) {
        final ShoppingList shoppingList = ShoppingListMapper.mapToEntity(dto);
        shoppingList.getItems().forEach(item -> item.setId(nextItemId()));
        shoppingList.setId(nextListId());
        shoppingList.setCreated(LocalDate.now());
        lists.put(shoppingList.getId(),shoppingList);
        return shoppingList;
    }
}
