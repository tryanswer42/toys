package be.vdab.toys.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.toys.domain.Order;
import be.vdab.toys.exceptions.RecordModifiedException;
import be.vdab.toys.services.OrderService;
import be.vdab.toys.services.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class UnshippedOrderController {

    private final OrderService orderService;
    private final ShippingService shippingService;


    public UnshippedOrderController(OrderService orderService, ShippingService shippingService) {
        this.orderService = orderService;

        this.shippingService = shippingService;
    }

    @GetMapping
    public ModelAndView orders() {

        ModelAndView modelAndView = new ModelAndView("unshippedOrders");
        return modelAndView.addObject("unshippedOrders", orderService.findAllByStatusExeptCancelledAndShipped());
        //return modelAndView.addObject("unshippedOrders", orderService.findAll());
    }

    @PostMapping
    public ModelAndView shippingChecked(@RequestParam(value = "toShip", required = false) List<Long> idList) {
        if (idList == null) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView modelAndView = new ModelAndView("unshippedOrders");
        try {List<Order> orderToShipList = orderService.findAllById(idList);
            modelAndView.addObject("ordersWithProblems", shippingService.shippingChecked(orderToShipList));
            modelAndView.addObject("unshippedOrders", orderService.findAllByStatusExeptCancelledAndShipped());

        }
        catch ( RecordModifiedException ex){
            modelAndView.addObject("unshippedOrders", orderService.findAllByStatusExeptCancelledAndShipped());
        }
       return modelAndView;
    }

}
