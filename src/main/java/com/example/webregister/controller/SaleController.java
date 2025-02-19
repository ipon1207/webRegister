package com.example.webregister.controller;

import com.example.webregister.model.Product;
import com.example.webregister.model.Sale;
import com.example.webregister.model.SaleDetail;
import com.example.webregister.model.UserPrincipal;
import com.example.webregister.service.ProductService;
import com.example.webregister.service.SaleDetailService;
import com.example.webregister.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SaleController {

    @Autowired
    ProductService productService;
    @Autowired
    SaleService saleService;
    @Autowired
    SaleDetailService saleDetailService;

    @GetMapping("/main/cashRegister")
    public String showCashRegisterPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        List<Product> productList;
        productList = productService.searchAllByUserId(userPrincipal.getUserId());
        model.addAttribute("productList", productList);
        return "sale/cashRegister";

    }

    @GetMapping("/main/saleLog")
    public String showSaleLogPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        List<Sale> saleLog;
        saleLog = saleService.searchAllByUserId(userPrincipal.getUserId());
        model.addAttribute("saleLog", saleLog);
        return "sale/saleLog";

    }

    @PostMapping("/checkout")
    public String checkOut(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Map<String, Object> checkoutData, Model model) {

        Long saleId;
        Long userId = userPrincipal.getUserId();
        int totalAmount = (int) checkoutData.get("totalPrice");
        int receivedAmount = (int) checkoutData.get("receivedAmount");
        int changeAmount = (int) checkoutData.get("changeAmount");
        saleId = saleService.insertSale(userId, totalAmount, receivedAmount, changeAmount);

        Map<String, Map<String, Object>> cart = (Map<String, Map<String, Object>>) checkoutData.get("cart");
        for (Map.Entry<String, Map<String, Object>> entry : cart.entrySet()) {
            try {

                String productIdStr = entry.getKey();
                Long productId = Long.parseLong(productIdStr);
                Map<String, Object> itemData = entry.getValue();
                String productName = (String) itemData.get("name");
                Integer productValue = (Integer) itemData.get("price");
                Integer quantity = (Integer) itemData.get("quantity");
                Integer amount = productValue*quantity;
                saleDetailService.insertSaleDetail(saleId, productId, productName, productValue,  quantity, amount);

            } catch (NumberFormatException e) {
                System.err.println("商品IDの変換に失敗: " + entry.getKey());
            } catch (ClassCastException e) {
                System.err.println("データ型の変換に失敗: " + entry.getValue());
            }
        }

        model.addAttribute("totalPrice", totalAmount);
        return "sale/checkout";
    }

    @PostMapping("/main/saleLog/detail")
    public String showSaleDetailLogPage(@RequestParam("id") Long saleId, Model model) {

        List<SaleDetail> SaleDetailLog;
        SaleDetailLog = saleDetailService.searchAllBySaleId(saleId);
        model.addAttribute("saleDetailLog", SaleDetailLog);
        return "sale/saleDetailLog";

    }

    @DeleteMapping("/main/saleLog/deleteLog")
    public String deleteSaleLog(@RequestParam("id") Long deleteId) {

        saleService.deleteBySaleId(deleteId);
        saleDetailService.deleteBySaleId(deleteId);
        return "redirect:/main";

    }

}