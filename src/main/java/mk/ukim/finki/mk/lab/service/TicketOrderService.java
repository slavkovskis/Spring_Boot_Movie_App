package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService {
    void placeOrder(String movieTitle, Long numberOfTickets, LocalDateTime dateTime);

    List<TicketOrder> listAll();
}
