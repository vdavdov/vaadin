package by.vdavdov.vaadin.repositories;

import by.vdavdov.vaadin.model.entity.Contact;
import by.vdavdov.vaadin.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    public ContactServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllContacts() {
        Contact contact = new Contact();
        contact.setId("1");
        contact.setName("John Doe");
        contact.setEmail("john@example.com");
        contact.setPhone("2020202");
        when(contactRepository.findAll()).thenReturn(Collections.singletonList(contact));

        var contacts = contactService.getAllContacts();
        assertEquals(1, contacts.size());
        assertEquals("John Doe", contacts.iterator().next().getName());
    }

    @Test
    void shouldReturnContactByName() {
        Contact contact = new Contact();
        contact.setId("1");
        contact.setName("John Doe");
        contact.setEmail("john@example.com");
        contact.setPhone("2020202");
        when(contactRepository.findByNameContaining("John Doe")).thenReturn(List.of(contact));

        var foundContact = contactRepository.findByNameContaining("John Doe");
        assertEquals("John Doe", foundContact.get(0).getName());
    }
}
