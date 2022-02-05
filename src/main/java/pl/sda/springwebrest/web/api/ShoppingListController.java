package pl.sda.springwebrest.web.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.springwebrest.business.service.ShoppingListService;
import pl.sda.springwebrest.config.SecurityRestConfiguration;
import pl.sda.springwebrest.data.dto.ShoppingListDto;
import pl.sda.springwebrest.data.entity.Item;
import pl.sda.springwebrest.data.entity.ShoppingList;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shoppinglists")
public class ShoppingListController {

    private final SecurityRestConfiguration configuration;

    private final ShoppingListService service;

    public ShoppingListController(SecurityRestConfiguration configuration, @Qualifier("jpaService") ShoppingListService service) {
        this.configuration = configuration;
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
    public ResponseEntity<ShoppingList> add(@Valid @RequestBody ShoppingListDto list){
        final ShoppingList save = service.save(list);
        return ResponseEntity.created(URI.create("/api/shoppinglists/" + save.getId()))
                .body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShoppingList> delete(@PathVariable long id){
        if (service.delete(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //TODO zmień metodę update w serwisie, aby zmieniała tylko name i owner w liscie
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> update(@Valid @RequestBody ShoppingListDto dto, @PathVariable long id){
        return service.update(dto, id) != null
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}/items")
    public ResponseEntity<List<Item>> updateItems(){
        //TODO zdefiniuj metodę wykonująca update zbiór składników listy
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/config")
    public ResponseEntity<Map<String, Object>> config(){
        Map<String, Object> result = new HashMap<>();
        result.put("page", configuration.getPage());
        result.put("theme", configuration.getTheme());
        return ResponseEntity.ok(result);
    }
}
