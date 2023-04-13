package com.jiho.shopping.service;

import com.jiho.shopping.entity.Item;
import com.jiho.shopping.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    public Item itemView(Integer id){
        return itemRepository.findById(id).get();
    }

    public List<Item> allItemView(){
        return itemRepository.findAll();
    }

    public void itemModify(Item item, Integer id){
        Item update = itemRepository.findItemById(id);
        update.setName(item.getName());
        update.setContents(item.getContents());
        update.setPrice(item.getPrice());
        update.setCategory(item.getCategory());
        update.setTitle(item.getTitle());
        itemRepository.save(update);
    }

    public void itemDelete(Integer id){
        itemRepository.deleteById(id);
    }
}
