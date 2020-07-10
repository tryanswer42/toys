package be.vdab.toys.services;

import be.vdab.toys.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllByStatusExeptCancelledAndShipped();

    List<Order> findAllById(List<Long> idList);


    Optional<Order> findById(long id);

    List<Order> findAll();
}
