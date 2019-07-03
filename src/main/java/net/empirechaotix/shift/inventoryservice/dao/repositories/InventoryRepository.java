package net.empirechaotix.shift.inventoryservice.dao.repositories;

import net.empirechaotix.shift.inventoryservice.dao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Item, Long> {

    List<Item> findAllById(Long id);

}
