package pl.sda.springwebrest.data.mapper;

import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.Item;
import pl.sda.springwebrest.data.entity.ShoppingList;

import java.util.HashSet;
import java.util.Set;

public class ShoppingListMapper {

    public static ShoppingList mapToEntity(ShoppingListDto dto){
        Set<Item> items = new HashSet<>();
        long id = 0;
        for(String itemName: dto.getItems()){
            items.add(Item.builder().id(++id).name(itemName).build());
        }
        return ShoppingList.builder()
                .owner(dto.getOwner())
                .name(dto.getName())
                .items(items)
                .build();
    }
}
