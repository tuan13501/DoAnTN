package com.ict.group06.travelwala.mapstruct.mappers;

import com.ict.group06.travelwala.contact.entity.Contact;
import com.ict.group06.travelwala.model.request.ContactRequest;
import com.ict.group06.travelwala.model.response.ContactResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {
    Contact contactRequestToContact(ContactRequest contactRequest);
    ContactResponse contactToContactResponse(Contact contact);
}
