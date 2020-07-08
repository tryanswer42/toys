package be.vdab.toys.services;

import be.vdab.toys.domain.Order;

import java.util.List;
import java.util.Set;

public interface ShippingService {

    Set<Long> shippingChecked(List<Order> orderToShipList);
}
