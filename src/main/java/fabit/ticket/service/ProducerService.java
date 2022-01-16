package fabit.ticket.service;

import fabit.ticket.model.Ticket;
import fabit.ticket.model.TicketAVRO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.function.Supplier;

@Service
public class ProducerService {
    private LinkedList<TicketAVRO> queue = new LinkedList<>();

    @Bean
    public Supplier<TicketAVRO> supplier() {
        return () -> {
            return queue.pollFirst();
        };
    }

    public void send(Ticket ticket) {
        queue.addLast(ticket.buildAVRO());
    }
}
