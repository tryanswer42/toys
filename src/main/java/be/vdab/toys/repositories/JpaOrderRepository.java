package be.vdab.toys.repositories;

import be.vdab.toys.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaOrderRepository {/*implements OrderRepository{

    private final EntityManager manager;

    public JpaOrderRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllInList(List<Long> idList) {
        return   manager.createNamedQuery("Order.findAllInList", Order.class)
                .setParameter("idList", idList)
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph(Order.WITH_CUSTOMERSANDCOUNTRY))
                .getResultList();
    }


    @Override
    public List<Order> findAllInListWithoutCustomersAndOrderdetails(List<Long> idList) {
        return null;
    }*/
}
