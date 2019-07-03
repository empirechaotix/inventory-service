package net.empirechaotix.shift.inventoryservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Wrapper {

    List<InventoryItem> inventoryItems;
    Version version;

}
