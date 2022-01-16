package fabit.ticket.service;

import fabit.ticket.model.Ticket;
import fabit.ticket.model.TicketAVRO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.prefs.Preferences;

@Service
public class ConsumerService {

    private String countKey = "countKey";
    private TicketAVRO lastTicket;

    @Bean
    Consumer<TicketAVRO> receive() {
        return ticket -> {
            lastTicket = ticket;
            incrementCount();
        };
    }

    public Ticket getLastTicket() {
        return lastTicket != null ? new Ticket(lastTicket) : null;
    }

    public Integer getCount() {
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        return prefs.getInt(countKey, 0);
    }

    private Integer incrementCount() {
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        Integer result = prefs.getInt(countKey, 0);
        result++;
        prefs.putInt(countKey, result);
        return result;
    }

}
