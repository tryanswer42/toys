package be.vdab.toys.controllers;


import be.vdab.toys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @GetMapping
    public ModelAndView orders() {

        ModelAndView modelAndView = new ModelAndView("unshippedOrders");
        return modelAndView.addObject("unshippedOrders", orderService.findAllByStatusExeptCancelledAndShipped());
    }


}
