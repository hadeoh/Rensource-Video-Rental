package com.rensource.videorental.unit;

import com.rensource.videorental.models.Order;
import com.rensource.videorental.repositories.OrderRepository;
import com.rensource.videorental.services.orders.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrder() {
        Order order = new Order("Mortal Kombat", "Lokesh", 8, new BigDecimal(178.00));
        orderService.makeOrder(order);
        verify(orderService, times(1)).makeOrder(order);
    }
}
