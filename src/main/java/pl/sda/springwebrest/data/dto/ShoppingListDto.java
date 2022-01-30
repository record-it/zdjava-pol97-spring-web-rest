package pl.sda.springwebrest.data.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ShoppingListDto {
    private String name;
    private String owner;
    private Set<String> items;
}
