package fabit.ticket.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Schema(description = "Билет на мероприятие")
public class Ticket {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotBlank
    @Schema(description = "ФИО посетителя", example = "Максимов Илья Николаевич")
    private String visitorName;
    @Schema(description = "Дата рождения посетителя")
    private LocalDate visitorBirthday;
    @Schema(description = "Наименование мероприятия")
    private String eventName;
    @NotNull
    @Schema(description = "Дата и время начала мероприятия", example = "2022-01-23T19:00")
    private LocalDateTime eventDateTime;
    @Min(1) @Max(32)
    @Schema(description = "Ряд")
    private Integer row;
    @Min(1) @Max(24)
    @Schema(description = "Место")
    private Integer seat;
    @NotNull
    @Schema(description = "Стоимость билета")
    private Double price;

    public Ticket() {

    }

    public Ticket(TicketAVRO ticketAVRO) {
        this.id = ticketAVRO.getId();
        this.visitorName = ticketAVRO.getVisitorName().toString();
        if (ticketAVRO.getVisitorBirthday() != null) {
            this.visitorBirthday = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(ticketAVRO.getVisitorBirthday()));
        }
        if (ticketAVRO.getEventName() != null) {
            this.eventName = ticketAVRO.getEventName().toString();
        }
        this.eventDateTime = LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(ticketAVRO.getEventDateTime()));
        this.row = ticketAVRO.getRow();
        this.seat = ticketAVRO.getSeat();
        this.price = ticketAVRO.getPrice();
    }

    public TicketAVRO buildAVRO() {
        TicketAVRO ticketAVRO = new TicketAVRO();
        ticketAVRO.setId(this.id);
        ticketAVRO.setVisitorName(this.visitorName);
        if (this.visitorBirthday != null) {
            ticketAVRO.setVisitorBirthday(DateTimeFormatter.ISO_DATE.format(this.visitorBirthday));
        }
        ticketAVRO.setEventName(this.eventName);
        ticketAVRO.setEventDateTime(DateTimeFormatter.ISO_DATE_TIME.format(this.eventDateTime));
        ticketAVRO.setRow(this.row);
        ticketAVRO.setSeat(this.seat);
        ticketAVRO.setPrice(this.price);
        return ticketAVRO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public LocalDate getVisitorBirthday() {
        return visitorBirthday;
    }

    public void setVisitorBirthday(LocalDate visitorBirthday) {
        this.visitorBirthday = visitorBirthday;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
