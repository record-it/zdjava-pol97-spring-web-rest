package pl.sda.springwebrest.web.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.springwebrest.business.service.ShoppingListService;
import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.ShoppingList;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> get(@PathVariable long id){
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ShoppingList> add(@RequestBody ShoppingListDto list){
        final ShoppingList save = service.save(list);
        return ResponseEntity.created(URI.create("/api/shoppinglists/" + save.getId()))
                .body(save);
    }

}
