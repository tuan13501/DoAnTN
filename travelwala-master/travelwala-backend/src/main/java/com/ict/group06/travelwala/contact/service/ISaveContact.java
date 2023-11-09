package com.ict.group06.travelwala.contact.service;

import com.ict.group06.travelwala.model.request.ContactRequest;
import com.ict.group06.travelwala.model.response.ContactResponse;

public interface ISaveContact {
    ContactResponse saveContact(ContactRequest contact);
}
