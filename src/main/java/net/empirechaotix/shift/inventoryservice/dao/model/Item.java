package net.empirechaotix.shift.inventoryservice.dao.model;

import lombok.Data;
import net.empirechaotix.shift.inventoryservice.model.InventoryItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="quantity")
    private Integer quantity;

    public InventoryItem convertToInventoryItem() {

        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setId(this.id);
        inventoryItem.setName(this.name);
        inventoryItem.setQuantity(this.quantity);

        return inventoryItem;
    }
}
