package com.jiho.shopping.controller;

import com.jiho.shopping.config.auth.PrincipalDetails;
import com.jiho.shopping.entity.Item;
import com.jiho.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PageController {
    private final ItemService itemService;
    @GetMapping("/main")
    public String main(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
/*        List<Item>items = itemService.allItemView();
        model.addAttribute("items",items);
        model.addAttribute("user",principalDetails.getUser());*/

        return "main";
    }
}
