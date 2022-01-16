package fabit.ticket.controller;

import fabit.ticket.model.Ticket;
import fabit.ticket.service.ProducerService;
import fabit.ticket.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestControllerAdvice
@RestController
@RequestMapping(value = "/api/ticket")
@Tag(name = "Управление билетами", description = "API для регистрации билетов на мероприятия")
public class TicketController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private ProducerService producerService;

    @Operation(summary = "Добавление билета")
    @PostMapping
    public ResponseEntity<Ticket> create(@Valid @RequestBody Ticket ticket) {
        storageService.create(ticket);
        producerService.send(ticket);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Получение информации о билете по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найденный билет",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Билет не найден",
                    content = @Content) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> read(@PathVariable(name = "id") long id) {
        final Ticket ticket = storageService.read(id);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
