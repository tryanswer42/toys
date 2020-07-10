package be.vdab.toys.repositories;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @EntityGraph(Order.WITH_CUSTOMERSANDCOUNTRY)
    List<Order> findAll();

    @Override
    @EntityGraph(Order.WITH_CUSTOMERSANDCOUNTRY)
    Optional<Order> findById(Long aLong);
    //    List<Order> findAllInList(List<Long> idList);
//    List<Order> findAllInListWithoutCustomersAndCountry(List<Long> idList);

}
