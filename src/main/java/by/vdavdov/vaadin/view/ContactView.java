package by.vdavdov.vaadin.view;

import by.vdavdov.vaadin.components.ContactEditor;
import by.vdavdov.vaadin.model.entity.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import by.vdavdov.vaadin.service.ExportService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class ContactView extends VerticalLayout {
    private final ContactRepository contactRepository;
    private final ContactEditor contactEditor;
    private final ExportService exportService;

    private final Grid<Contact> grid = new Grid<>(Contact.class);;

    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("Add new contact", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);


    @Autowired
    public ContactView(ContactRepository contactRepository, ContactEditor contactEditor, ExportService exportService) {
        this.contactRepository = contactRepository;
        this.contactEditor = contactEditor;
        this.exportService = exportService;

        Button exportButton = new Button("Экспортировать в Excel", clickEvent -> {
            getUI().ifPresent(ui -> {
                ui.getPage().executeJs("window.location.href = 'export';");
            });
        });

        filter.setPlaceholder("Write to filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, grid, contactEditor, exportButton);

        grid.asSingleSelect().addValueChangeListener(e -> {
            contactEditor.editContact(e.getValue());
        });

        addNewButton.addClickListener(e -> contactEditor.editContact(new Contact()));

        contactEditor.setChangeHandler(() -> {
            contactEditor.setVisible(false);
            fillList(filter.getValue());
        });

        fillList("");
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            grid.setItems(contactRepository.findAll());
        } else {
            grid.setItems(contactRepository.findByNameContaining(name));
        }
    }
}
