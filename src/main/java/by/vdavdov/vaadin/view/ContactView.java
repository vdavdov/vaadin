package by.vdavdov.vaadin.view;

import by.vdavdov.vaadin.model.Contact;
import by.vdavdov.vaadin.service.ContactService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class ContactView extends VerticalLayout {
    private final ContactService contactService;
    private final Grid<Contact> grid;


    @Autowired
    public ContactView(ContactService contactService) {
        this.contactService = contactService;
        this.grid = new Grid<>(Contact.class);

        add(grid);
        listContact();
    }

    private void listContact() {
        grid.setItems(contactService.getAllContacts());
    }

}
