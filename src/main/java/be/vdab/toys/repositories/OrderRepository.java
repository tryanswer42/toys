package be.vdab.toys.repositories;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAll();
//    List<Order> findAllInList(List<Long> idList);
//    List<Order> findAllInListWithoutCustomersAndCountry(List<Long> idList);

}
