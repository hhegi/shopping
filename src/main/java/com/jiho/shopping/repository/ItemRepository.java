package com.jiho.shopping.repository;

import com.jiho.shopping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findItemByIdx(Integer idx);
}
