package fabit.ticket.controller;

import fabit.ticket.model.Ticket;
import fabit.ticket.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {
        Integer count = consumerService.getCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/lastTicket")
    public ResponseEntity<Ticket> getLastTicket() {
        final Ticket ticket = consumerService.getLastTicket();
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }
}
