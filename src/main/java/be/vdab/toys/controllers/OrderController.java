package be.vdab.toys.controllers;
/**
 * @author Mulangu C
 */
import be.vdab.toys.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public ModelAndView showOrder(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("order");
        orderService.findById(id).ifPresent(order -> modelAndView.addObject("order",order));
        return modelAndView;
    }
}
