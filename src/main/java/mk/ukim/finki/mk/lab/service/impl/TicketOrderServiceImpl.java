package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.TicketOrder;
import mk.ukim.finki.mk.lab.repository.jpa.TicketOrderRepository;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }

    @Override
    public void placeOrder(String movieTitle, Long numberOfTickets, LocalDateTime dateTime) {
        ticketOrderRepository.save(new TicketOrder(movieTitle, numberOfTickets, dateTime));
    }

    @Override
    public List<TicketOrder> listAll() {
        return ticketOrderRepository.findAll();
    }

}
