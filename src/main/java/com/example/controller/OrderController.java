package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.OrderItemDTO;
import com.example.dto.OrderRequestForm;
import com.example.entity.OrderRequest;
import com.example.entity.OrderRequestDetail;
import com.example.entity.Store;
import com.example.entity.User;
import com.example.repository.ItemRepository;
import com.example.repository.OrderRequestRepository;
import com.example.security.CustomUserDetails;
import com.example.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final OrderRequestRepository orderRequestRepository;

    public OrderController(OrderService orderService, ItemRepository itemRepository, OrderRequestRepository orderRequestRepository) {
        this.orderService = orderService;
        this.itemRepository = itemRepository;
        this.orderRequestRepository = orderRequestRepository;
    }

    @GetMapping
    public String showOrderRequestForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<OrderItemDTO> orderItems = orderService.getOrderItemDTOsByStore(userDetails.getStore());
        model.addAttribute("orderItems", orderItems);
        return "order/request_form";
    }

    @PostMapping("/request")
    public String submitOrder(@ModelAttribute OrderRequestForm form, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Store store = userDetails.getStore();
        User user = userDetails.getUser();

        OrderRequest request = new OrderRequest();
        request.setStore(store);
        request.setRequestDate(LocalDateTime.now());
        request.setUser(user);

        List<OrderRequestDetail> details = form.getDetails().stream()
            .filter(i -> i.getQuantity() != null && i.getQuantity() > 0)
            .map(i -> {
                OrderRequestDetail detail = new OrderRequestDetail();
                detail.setItem(itemRepository.findById(i.getItemId()).orElseThrow());
                detail.setQuantity(i.getQuantity());
                detail.setOrderRequest(request);
                return detail;
            }).toList();

        if (details.isEmpty()) {
            return "redirect:/order/request?error=empty";
        }

        request.setDetails(details);
        orderRequestRepository.save(request);

        return "redirect:/order";
    }

    @GetMapping("/list")
    public String showOrderDetails(@RequestParam(name = "requestId", required = false) Long requestId, Model model) {
        List<OrderRequest> requests = orderRequestRepository.findAllByOrderByRequestDateDesc();
        model.addAttribute("requests", requests);

        // requestId に対応する OrderRequestDetail → List<OrderItemDTO> を取得
        List<OrderItemDTO> details = orderService.getOrderDetailsByRequestId(requestId);
        model.addAttribute("orderDetails", details);
        return "order/request_list";
    }



}
