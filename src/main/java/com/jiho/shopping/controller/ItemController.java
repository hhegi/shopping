package com.jiho.shopping.controller;

import com.jiho.shopping.config.auth.PrincipalDetails;
import com.jiho.shopping.entity.Item;
import com.jiho.shopping.entity.User;
import com.jiho.shopping.service.ItemService;
import com.jiho.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ItemController{

    private final ItemService itemService;
    private final UserService userService;

    @GetMapping("/item/add")
    public String itemAddForm(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        model.addAttribute("user", userDetails.getUser());
        return "/upload";
    }

    @PostMapping("/item/add/post")
    public String itemAdd(@AuthenticationPrincipal PrincipalDetails userDetails, MultipartFile imgFile, Item item) throws Exception{
        item.setUser(userDetails.getUser());
        itemService.saveItem(item, imgFile);
        return "redirect:/main";
    }

    @GetMapping("/item/modify/{id}")
    public String itemModifyForm(@PathVariable("id") Integer id,@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        User user = itemService.itemView(id).getUser();
        if(user.getId()==userDetails.getUser().getId()){
            model.addAttribute("item", itemService.itemView(id));
            model.addAttribute("user", userDetails.getUser());
            return "/modify";
        }else
            return "redirect:/main";
    }

    @PostMapping("/item/modify/post/{id}")
    public String itemModify(PrincipalDetails userDetails,Item item, @PathVariable("id") Integer id, MultipartFile img) throws Exception{
        User user = itemService.itemView(id).getUser();

        if(user.getId()==userDetails.getUser().getId()){
            itemService.itemModify(item,id, img);
            return "redirect:/main";
        }

        return "redirect:/main";
    }

    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id){
        itemService.itemDelete(id);

        return "redirect:/main";
    }

}
