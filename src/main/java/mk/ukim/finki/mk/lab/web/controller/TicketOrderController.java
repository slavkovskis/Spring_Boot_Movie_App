package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.repository.jpa.MovieRepository;
import mk.ukim.finki.mk.lab.service.TicketOrderService;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieRepository movieRepository;

    public TicketOrderController(TicketOrderService ticketOrderService, UserService userService, MovieRepository movieRepository) {
        this.ticketOrderService = ticketOrderService;
        this.movieRepository = movieRepository;
    }

    @GetMapping()
    public String getPage(Model model) {
        model.addAttribute("tickets", ticketOrderService.listAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

        }
        return "orderConfirmation";
    }

    @PostMapping("/add")
    public String ticketOrder(@RequestParam String movieTitle,
                              @RequestParam Long numTickets,
                              @RequestParam(value = "dateCreated")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        ticketOrderService.placeOrder(movieRepository.findMoviesByTitleIgnoreCaseContaining(movieTitle).get(0).getTitle(), numTickets, dateTime);
        return "redirect:/ticketOrder";
    }

}
