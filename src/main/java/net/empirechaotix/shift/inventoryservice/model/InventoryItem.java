package net.empirechaotix.shift.inventoryservice.model;

import lombok.Data;
import net.empirechaotix.shift.inventoryservice.dao.model.Item;

@Data
public class InventoryItem {

    private Long id;
    private String name;
    private Integer quantity;

    public Item convertToItem() {
        Item item = new Item();

        item.setId(this.id);
        item.setName(this.name);
        item.setQuantity(this.quantity);

        return item;
    }

}

