package com.rensource.videorental;

import com.rensource.videorental.controllers.OrderController;
import com.rensource.videorental.controllers.VideoController;
import com.rensource.videorental.services.orders.OrderService;
import com.rensource.videorental.services.videos.VideoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class VideoRentalServiceApplicationTests {

	@Autowired
	VideoController videoController;

	@Autowired
	OrderController orderController;

	@Autowired
	OrderService orderService;

	@Autowired
	VideoService videoService;

	@Test
	public void contextLoads() {
		Assertions.assertThat(videoController).isNotNull();
		Assertions.assertThat(orderController).isNotNull();
		Assertions.assertThat(orderService).isNotNull();
		Assertions.assertThat(videoService).isNotNull();
	}

}
