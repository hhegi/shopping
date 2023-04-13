package com.jiho.shopping.controller;

import com.jiho.shopping.entity.Item;
import com.jiho.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController{

    private final ItemService itemService;

    @GetMapping("/item/add")
    public String itemAddForm(){
        return "/item/addForm";
    }

    @PostMapping("/item/add/post")
    public String itemAdd(Item item){
        itemService.saveItem(item);
        return "/main";
    }

    @GetMapping("/item/modify/{id}")
    public String itemModifyForm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("item", itemService.itemView(id));

        return "/item/modifyForm";
    }

    @PostMapping("/item/modify/post/{id}")
    public String itemModify(Item item, @PathVariable("id") Integer id){
        itemService.itemModify(item,id);

        return "redirect:/main";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id){
        itemService.itemDelete(id);

        return "/main";
    }

}
