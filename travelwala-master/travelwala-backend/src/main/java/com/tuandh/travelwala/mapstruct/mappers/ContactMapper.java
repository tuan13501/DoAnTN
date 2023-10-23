package com.tuandh.travelwala.mapstruct.mappers;

import com.tuandh.travelwala.contact.entity.Contact;
import com.tuandh.travelwala.model.request.ContactRequest;
import com.tuandh.travelwala.model.response.ContactResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
    Contact contactRequestToContact(ContactRequest contactRequest);
    ContactResponse contactToContactResponse(Contact contact);
}
