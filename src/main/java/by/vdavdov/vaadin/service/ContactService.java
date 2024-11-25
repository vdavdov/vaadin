package by.vdavdov.vaadin.service;

import by.vdavdov.vaadin.model.entity.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<Contact> filterByName(String name) {
        return contactRepository.findByNameContaining(name);
    }

    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public Optional<Contact> findById(String id) {
        return contactRepository.findById(id);
    }
}
