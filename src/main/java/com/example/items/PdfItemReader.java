package com.example.items;

import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfItemReader implements ItemReader<Resource> {

    private List<File> pdfFiles;
    private int nextPdfFileIndex;

    public PdfItemReader() {
        // Initialisez la liste des fichiers PDF à lire dans le constructeur.
        pdfFiles = new ArrayList<>();
        File directory = new File("C:/Users/khawla/Desktop/PDFS");
        File[] pdfFilesArray = directory.listFiles((dir, name) -> name.endsWith(".pdf"));
        if (pdfFilesArray != null) {
            for (File file : pdfFilesArray) {
                pdfFiles.add(file);
            }
        }
        nextPdfFileIndex = 0;
    }

    @Override
    public Resource read() {
        if (nextPdfFileIndex < pdfFiles.size()) {
            File pdfFile = pdfFiles.get(nextPdfFileIndex++);
            if (pdfFile != null) {
                return new FileSystemResource(pdfFile);
            }
        }
        return null;
    }
}
