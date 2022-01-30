package pl.sda.springwebrest.data.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class ShoppingListDto {
    @Length(min = 3, message = "Nazwa nie może być krótsza niż 3 znaki!")
    private String name;
    private String owner;
    @Size(min=1, message = "Lista musi posiadać choć jeden element!")
    private Set<String> items;
}
