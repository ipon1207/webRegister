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
        productList = productService.searchAllByUserId(userPrincipal.getUserId());
        model.addAttribute("productList", productList);
        return "product/productList";

    }

    @PostMapping("/productList/editProduct")
    public String showProductEditPage(@RequestParam("id") Long productId, Model model) {

        Product editProduct;
        editProduct = productService.searchByProductId(productId);
        model.addAttribute("editProduct", editProduct);
        return "product/productEdit";

    }

    @PostMapping("/productList/updateProduct")
    public String updateProduct(@ModelAttribute Product updateProduct) {

        productService.updateByProductId(updateProduct);
        return "redirect:/main/productList";

    }

    @DeleteMapping("/productList/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long deleteProductId) {

        productService.deleteByProductId(deleteProductId);
        return "redirect:/main/productList";

    }

}