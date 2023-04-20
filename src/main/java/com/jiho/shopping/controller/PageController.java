package com.jiho.shopping.controller;

import com.jiho.shopping.config.auth.PrincipalDetails;
import com.jiho.shopping.entity.Item;
import com.jiho.shopping.service.ItemService;
import com.jiho.shopping.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PageController {
    private final ItemService itemService;
    private final UserService userService;
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal PrincipalDetails user,Model model){
        int id = user.getUser().getId();
        List<Item> items = itemService.allItemView();
        model.addAttribute("user",userService.findUser(id));
        model.addAttribute("items",items);

        return "/main";
    }
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/user/{id}")
    public String myPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails userDetails){
        if(userDetails.getUser().getId()==id){
            model.addAttribute("user", userService.findUser(id));

            return "/mypage";
        }else{

            return "redirect:/main";
        }
    }

    @GetMapping("/user/list/{id}")
    public String myItemList(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("id")Integer id
                            ,Model model){
        if(userDetails.getUser().getId()==id){
            List<Item> allItem = itemService.allItemView();
            List<Item> myItem = new ArrayList<>();

            for(Item item: allItem){
                if(item.getUser().getId()==id){
                    myItem.add(item);
                }
            }

            model.addAttribute("user",userService.findUser(id));
            model.addAttribute("myItem",myItem);

            return "/myitemlist";
        }else{
            return "redirect:/main";
        }

    }
}
