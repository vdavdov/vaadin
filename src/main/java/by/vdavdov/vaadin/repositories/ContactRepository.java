package by.vdavdov.vaadin.repositories;

import by.vdavdov.vaadin.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findAll();

    Optional<Contact> findByName(String name);

    Optional<Contact> findByPhone(String phone);
}
