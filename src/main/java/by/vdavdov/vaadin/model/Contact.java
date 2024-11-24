package by.vdavdov.vaadin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
@AllArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String phone;

    @Override
    public String toString() {
        return String.format(
                "Contact[id=%s, " +
                        "name='%s'," +
                        "email='%s'," +
                        "phone='%s']",
        id, name, email, phone);
    }
}
