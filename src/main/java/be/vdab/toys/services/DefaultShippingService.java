package be.vdab.toys.services;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultShippingService implements ShippingService {


    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Set<Long> shippingChecked(List<Order> orderToShipList) {

        Set<Long> ordersWithProblems = new LinkedHashSet<>();
        AtomicBoolean readyToShip = new AtomicBoolean(true);
        
        orderToShipList.stream().forEach(order ->
        {
            order.getOrderdetails().forEach(orderdetail ->
            {
                if (!orderdetail.isDeliverable()) {
                    readyToShip.set(false);
                }
            });
            if (readyToShip.get()) {
                order.getOrderdetails().forEach(orderdetail ->
                {
                    try {
                        orderdetail.getProduct().loweringInOrder(orderdetail.getOrdered());
                        orderdetail.getProduct().loweringInStock(orderdetail.getOrdered());
                        order.setStatus(Status.SHIPPED);
                        order.setShipped(LocalDate.now());
                    } catch (IllegalArgumentException exception) {
                        ordersWithProblems.add(order.getId());
                    }
                });
            }
            else {
                ordersWithProblems.add(order.getId());
            }
        });
        return ordersWithProblems;
    }
}