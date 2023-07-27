package com.example.items;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public class PdfItemProcessor implements ItemProcessor<Resource, Resource> {

    @Override
    public Resource process(Resource pdfFileResource) throws IOException {
        // Votre logique de traitement ici...
        // Par exemple, renommez le fichier avec un préfixe "OLD_".
        String oldName = pdfFileResource.getFile().getAbsolutePath();
        String newName = pdfFileResource.getFile().getParent() + "/OLD_" + pdfFileResource.getFile().getName();
        File newFile = new File(newName);

        if (pdfFileResource.getFile().renameTo(newFile)) {
            // Retournez la ressource du fichier renommé et copié pour le traitement suivant (ItemWriter).
            return new FileSystemResource(newFile);
        } else {
            // Gérez ici le cas où le renommage a échoué.
            // Vous pouvez choisir de lever une exception ou de retourner null pour ignorer ce fichier.
            // Par exemple, vous pouvez faire ceci :
            throw new IOException("Impossible de renommer le fichier : " + pdfFileResource.getFilename());
            // Ou simplement :
            // return null;
        }
    }
}
