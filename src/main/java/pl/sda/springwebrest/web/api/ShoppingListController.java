package pl.sda.springwebrest.web.api;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.springwebrest.buisness.service.ShoppingListService;
import pl.sda.springwebrest.data.entity.ShoppingList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shoppinglists")
public class ShoppingListController {
    private final ShoppingListService service;

    public ShoppingListController(@Qualifier("memoryService") ShoppingListService service) {
        this.service = service;
    }

    @RequestMapping("/examples/dynamic")
    public Map<String, Object> get(){
        Map<String, Object> list = new HashMap<>();
        list.put("mleko", "2 litry");
        list.put("chleb", "1 bochenek");
        list.put("masło", "200 g");
        Map<String, Object> bulki = new HashMap<>();
        bulki.put("liczba", 6);
        bulki.put("rodzaj", "wielozarnista");
        bulki.put("rozmiar", "małe");
        list.put("bułki", bulki);
        return list;
    }

    @GetMapping("")
    public List<ShoppingList> getAll(){
        return service.findAll();
    }
}
