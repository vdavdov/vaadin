package by.vdavdov.vaadin.repositories;

import by.vdavdov.vaadin.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
    public Collection<Contact> finaAll();
}
