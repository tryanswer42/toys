package be.vdab.toys.services;


import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import be.vdab.toys.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAllByStatusExeptCancelledAndShipped(){
        return orderRepository.findAll().stream().filter(order -> order.getStatus()!=Status.CANCELLED && order.getStatus()!=Status.SHIPPED).collect(Collectors.toList());
    }
}
