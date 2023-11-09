package com.ict.group06.travelwala.contact.service.impl;

import com.ict.group06.travelwala.contact.entity.Contact;
import com.ict.group06.travelwala.contact.repository.ContactRepository;
import com.ict.group06.travelwala.contact.service.ISaveContact;
import com.ict.group06.travelwala.mapstruct.mappers.ContactMapper;
import com.ict.group06.travelwala.model.request.ContactRequest;
import com.ict.group06.travelwala.model.response.ContactResponse;
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
