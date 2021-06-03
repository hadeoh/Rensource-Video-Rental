package com.rensource.videorental.unit;

import com.rensource.videorental.controllers.OrderController;
import com.rensource.videorental.controllers.VideoController;
import com.rensource.videorental.dtos.OrderDto;
import com.rensource.videorental.models.Order;
import com.rensource.videorental.responses.Response;
import com.rensource.videorental.services.orders.OrderService;
import com.rensource.videorental.services.videos.VideoService;
import com.rensource.videorental.testconfigurations.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
@Slf4j
public class OrderControllerTest {

    @MockBean
    OrderController orderController;

    @MockBean
    OrderService orderService;

    @MockBean
    VideoService videoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void it_should_create_an_order() throws Exception {
        OrderDto orderDto = new OrderDto("An Army of The Dead", "Usman", 10);
        ResponseEntity<Response<Order>> order = orderController.makeOrder(orderDto);
        verify(orderController, times(1)).makeOrder(orderDto);

    }
}
