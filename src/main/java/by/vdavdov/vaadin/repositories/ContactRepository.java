package by.vdavdov.vaadin.repositories;

import by.vdavdov.vaadin.model.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

    List<Contact> findByNameContaining(@Param("name") String name);
}
