package com.ict.group06.travelwala.ticket.repository;

import com.ict.group06.travelwala.ticket.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
