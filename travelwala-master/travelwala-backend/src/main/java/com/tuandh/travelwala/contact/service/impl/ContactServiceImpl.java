package com.tuandh.travelwala.contact.service.impl;

import com.tuandh.travelwala.contact.entity.Contact;
import com.tuandh.travelwala.contact.repository.ContactRepository;
import com.tuandh.travelwala.contact.service.ISaveContact;
import com.tuandh.travelwala.mapstruct.mappers.ContactMapper;
import com.tuandh.travelwala.model.request.ContactRequest;
import com.tuandh.travelwala.model.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ISaveContact {
    private final ContactRepository contactRepository;
    private final ContactMapper mapper = Mappers.getMapper(ContactMapper.class);

    @Override
    public ContactResponse saveContact(ContactRequest contact) {
        Contact savedContact = contactRepository.save(mapper.contactRequestToContact(contact));
        return mapper.contactToContactResponse(savedContact);
    }
}
