package com.example.webregister.controller.product;

import com.example.webregister.model.Product;
import com.example.webregister.model.UserPrincipal;
import com.example.webregister.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productList")
    public String showProductListPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        List<Product> productList;
        if (userPrincipal.getRole().equals("ADMIN")) {
            productList = productService.searchAll();
            model.addAttribute("productList", productList);
            return "product/productList";
        }
        productList = productService.searchByUserId(userPrincipal.getUserId());
        model.addAttribute("productList", productList);
        return "product/productList";

    }

    @DeleteMapping("/productList/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long deleteProductId) {

        productService.deleteByProductId(deleteProductId);
        return "redirect:/main/productList";

    }

}