package com.charles.website.utils;

import com.charles.website.exception.BadRequestException;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageService {
    public static String saveImage(String path, MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String imageName = UUID.randomUUID().toString() + "." + extension;

        File file = new File(path + imageName);
        FileUtils.writeByteArrayToFile(file, image.getBytes());

        return imageName;
    }

    public static byte[] getImage(String pathFile) throws IOException {
        return FileUtils.readFileToByteArray(new File(pathFile));
    }

    public static void FileRemover(String pathFile) {
        File file = new File(pathFile);

        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) throw new BadRequestException(400, "Delete images error!");
        } else {
            throw new BadRequestException(400, "File does not exist.");
        }

    }
}
