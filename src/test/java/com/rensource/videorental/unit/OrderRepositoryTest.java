package com.rensource.videorental.unit;

import com.rensource.videorental.models.Order;
import com.rensource.videorental.repositories.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void test_make_order() {
        Order order = new Order("Mortal Kombat", "Lokesh", 8, new BigDecimal(178.00));
        orderRepository.saveAndFlush(order);
        Iterable<Order> orders = orderRepository.findAll();
        Assertions.assertThat(orders).extracting(Order::getRentedBy).contains("Lokesh");
    }
}
