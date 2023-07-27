package com.example.items;

import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PdfItemWriter implements ItemWriter<Resource> {

    @Override
    public void write(List<? extends Resource> pdfFileResources) throws IOException {
        // Votre logique pour écrire les fichiers PDF renommés et copiés.
        // Par exemple, copiez les fichiers dans un répertoire de destination.
        String destinationDirectory = "C:/Users/khawla/Desktop/PDFS";
        for (Resource pdfFileResource : pdfFileResources) {
            File sourceFile = pdfFileResource.getFile();
            String destinationFileName = destinationDirectory + "/" + sourceFile.getName();
            Files.copy(sourceFile.toPath(), Paths.get(destinationFileName));
        }
    }
}
