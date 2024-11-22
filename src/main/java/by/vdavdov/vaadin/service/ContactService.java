package by.vdavdov.vaadin.service;

import by.vdavdov.vaadin.model.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Collection<Contact> getAllContacts() {
        return contactRepository.finaAll();
    }
}
