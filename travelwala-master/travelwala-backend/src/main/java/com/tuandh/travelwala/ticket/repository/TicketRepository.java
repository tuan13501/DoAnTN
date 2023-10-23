package com.tuandh.travelwala.ticket.repository;

import com.tuandh.travelwala.ticket.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
