package by.vdavdov.vaadin.service;

import by.vdavdov.vaadin.model.entity.Contact;
import by.vdavdov.vaadin.repositories.ContactRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExportService {
    private final ContactRepository contactRepository;

    @Autowired
    public ExportService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public byte[] exportContactsToExcel() throws Exception {
        List<Contact> contacts = contactRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Phone");

        int rowNum = 1;
        for (Contact contact : contacts) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contact.getId());
            row.createCell(1).setCellValue(contact.getName());
            row.createCell(2).setCellValue(contact.getEmail());

            row.createCell(3).setCellValue(contact.getPhone());
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } finally {
            workbook.close();
        }
    }
}
