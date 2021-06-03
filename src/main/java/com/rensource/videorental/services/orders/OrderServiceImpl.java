package com.rensource.videorental.services.orders;

import com.rensource.videorental.models.Order;
import com.rensource.videorental.models.Video;
import com.rensource.videorental.repositories.OrderRepository;
import com.rensource.videorental.services.videos.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    VideoService videoService;
    OrderRepository orderRepository;

    @Value("${regular}")
    private String regularPrice;

    @Value("${children-movie}")
    private String childrenPrice;

    @Value("${new-release}")
    private String newReleasePrice;

    @Autowired
    public OrderServiceImpl(VideoService videoService, OrderRepository orderRepository) {
        this.videoService = videoService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order makeOrder(Order order) {
        Order newOrder = null;
        try {
            Video video = videoService.getVideo(order.getTitle());
            log.info("the video is {}", video.toString());
            if (video != null) {
                double price = 0.00;
                switch (video.getType()) {
                    case REGULAR:
                        price = Double.parseDouble(regularPrice) * order.getRentDays();
                        break;
                    case CHILDREN_MOVIE:
                        price = Double.parseDouble(childrenPrice) * order.getRentDays() + ((double) video.getMaximumAge() / 2);
                        break;
                    case NEW_RELEASE:
                        Date date = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        Double year = (double) calendar.get(Calendar.YEAR);
                        log.info("The year is " + year);
                        price = Double.parseDouble(newReleasePrice) * order.getRentDays() - (year - (double) video.getReleaseYear());
                }
                BigDecimal amount = new BigDecimal(price);
                amount = amount.setScale(2, RoundingMode.HALF_EVEN);
                order.setRentAmount(amount);

                newOrder = orderRepository.save(order);

            }

        } catch (Exception e) {
            log.info("An error was encountered while making an order for a vide due to {}", e.getMessage());
            e.printStackTrace();
        }
        return newOrder;
    }
}
