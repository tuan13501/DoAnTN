package com.tuandh.travelwala.contact.service;

import com.tuandh.travelwala.model.request.ContactRequest;
import com.tuandh.travelwala.model.response.ContactResponse;

public interface ISaveContact {
    ContactResponse saveContact(ContactRequest contact);
}
