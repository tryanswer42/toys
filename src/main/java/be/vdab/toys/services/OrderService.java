package be.vdab.toys.services;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllByStatusExeptCancelledAndShipped();
    Optional<Order> findById(long id);
}
