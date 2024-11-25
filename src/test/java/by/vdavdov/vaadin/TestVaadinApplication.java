package by.vdavdov.vaadin;

import org.springframework.boot.SpringApplication;

public class TestVaadinApplication {

    public static void main(String[] args) {
        SpringApplication.from(VaadinApplication::main).run(args);
    }

}
