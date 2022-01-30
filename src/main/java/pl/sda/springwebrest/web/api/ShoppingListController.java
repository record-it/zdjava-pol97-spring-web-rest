package pl.sda.springwebrest.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShoppingListController {

    @RequestMapping("/api/examples/dynamic")
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


}
