package by.vdavdov.vaadin.service;

import by.vdavdov.vaadin.model.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Collection<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
