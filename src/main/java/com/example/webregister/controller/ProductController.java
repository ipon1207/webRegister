package com.example.webregister.controller;

import com.example.webregister.model.Product;
import com.example.webregister.model.UserPrincipal;
import com.example.webregister.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/main/productList")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String showProductListPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        List<Product> productList;
        if (userPrincipal.getRole().equals("ADMIN")) {
            productList = productService.searchAll();
            model.addAttribute("productList", productList);
            return "product/productList";
        }
        productList = productService.searchAllByUserId(userPrincipal.getUserId());
        model.addAttribute("productList", productList);
        return "product/productList";

    }

    @GetMapping("/productRegister")
    public String showProductRegisterPage(Model model) {

        model.addAttribute("saveProduct", new Product());
        return "product/productRegister";

    }

    @PostMapping("/editProduct")
    public String showProductEditPage(@RequestParam("id") Long productId, Model model) {

        Product editProduct;
        editProduct = productService.searchByProductId(productId);
        model.addAttribute("editProduct", editProduct);
        return "product/productEdit";

    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product updateProduct) {

        productService.updateByProductId(updateProduct);
        return "redirect:/main";

    }

    @PostMapping("/productRegister/save")
    public String productSave(@ModelAttribute Product product, @AuthenticationPrincipal UserPrincipal userPrincipal) {

        productService.insertProduct(product, userPrincipal.getUserId());
        return "redirect:/main";

    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long deleteProductId) {

        productService.deleteByProductId(deleteProductId);
        return "redirect:/main";

    }

}