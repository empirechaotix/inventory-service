package net.empirechaotix.shift.inventoryservice.controller;

import net.empirechaotix.shift.inventoryservice.dao.model.Item;
import net.empirechaotix.shift.inventoryservice.dao.repositories.InventoryRepository;
import net.empirechaotix.shift.inventoryservice.model.InventoryItem;
import net.empirechaotix.shift.inventoryservice.model.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/v1/inventorymanagement/")
public class InventoryManagement {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryManagement(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PostMapping("")
    Resource<Wrapper> createStock(@RequestBody InventoryItem inventoryItem) {

        Wrapper response = new Wrapper();

        Item item = inventoryRepository.save(inventoryItem.convertToItem());

        response.getInventoryItems().add(item.convertToInventoryItem());

        return new Resource<>(response);
    }

    @DeleteMapping("/{id}")
    Resource<Wrapper> deleteStock(@PathVariable String id) {

        Wrapper response = new Wrapper();

        List<Item> inventoryItems = inventoryRepository.findAllById(Long.parseLong(id));

        if(inventoryItems.size() > 1) {
            //should not delete more than one item, this should never happen though, since id's should be unique
            return null;
        }

        //should only have one item
        for(Item item : inventoryItems) {
            response.getInventoryItems().add(item.convertToInventoryItem());
            inventoryRepository.delete(item);
        }

        return new Resource<>(response);
    }

    @PutMapping("/{id}/quantity/{quantity}")
    Resource<Wrapper> updateStock(@PathVariable String id, @PathVariable Integer quantity) {

        Wrapper response = new Wrapper();

        List<Item> inventoryItems = inventoryRepository.findAllById(Long.parseLong(id));

        if(inventoryItems.size() > 1) {
            //should not add store to more than one item, this should never happen though, since id's should be unique
            return null;
        }

        //should only have one item
        for(Item item : inventoryItems) {
            item.setQuantity(quantity);
            item = inventoryRepository.save(item);
            response.getInventoryItems().add(item.convertToInventoryItem());
        }

        return new Resource<>(response);
    }

    @GetMapping("")
    Resource<Wrapper> getAll() {
        Wrapper response = new Wrapper();

        List<Item> inventoryItems = inventoryRepository.findAll();

        //should only have one item
        for(Item item : inventoryItems) {
            response.getInventoryItems().add(item.convertToInventoryItem());
        }

        return new Resource<>(response);
    }

    @GetMapping("/{id}")
    Resource<Wrapper> getById(@PathVariable String id) {
        Wrapper response = new Wrapper();

        List<Item> inventoryItems = inventoryRepository.findAllById(Long.parseLong(id));

        //should only have one item
        for(Item item : inventoryItems) {
            response.getInventoryItems().add(item.convertToInventoryItem());
        }

        return new Resource<>(response);
    }

}
