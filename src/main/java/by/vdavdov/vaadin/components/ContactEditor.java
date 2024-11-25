package by.vdavdov.vaadin.components;

import by.vdavdov.vaadin.model.entity.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ContactEditor extends VerticalLayout implements KeyNotifier {
    private final ContactRepository contactRepository;
    private Contact contact;

    private TextField name = new TextField("", "Name");
    private TextField email = new TextField("", "Email");
    private TextField phone = new TextField("", "Phone");

    private Button saveButton = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancelButton = new Button("Cancel");
    private Button deleteButton = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(saveButton, cancelButton, deleteButton);

    private Binder<Contact> binder = new Binder<>(Contact.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public ContactEditor(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

        add(name, email, phone, actions);
        binder.bindInstanceFields(this);
        setSpacing(true);

        saveButton.getElement().getThemeList().add("primary");
        deleteButton.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        saveButton.addClickListener(e -> save());
        deleteButton.addClickListener(e -> delete());
        cancelButton.addClickListener(e -> editContact(contact));
        setVisible(false);
    }

    public void editContact(Contact newContact) {
        if (newContact == null) {
            setVisible(false);
            return;
        }

        contact = newContact;

        if (contact.getId() != null) {
            contactRepository.findById(contact.getId()).ifPresent(c -> contact = c);
        }

        binder.setBean(contact);
        setVisible(true);
        name.focus();
    }

    public void delete() {
        contactRepository.deleteById(contact.getId());
        changeHandler.onChange();
    }

    public void save() {
        contactRepository.save(contact);
        changeHandler.onChange();
    }
}
