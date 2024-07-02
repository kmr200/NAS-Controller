package nas.nascontroller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileService {

    private final Path rootPath = Path.of("/Users/owner/Downloads/Temp");

    public void saveFile(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            } else {
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, rootPath.resolve(file.getOriginalFilename()));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveMultipleFiles(MultipartFile[] files) {

        for (MultipartFile file : files) {
            saveFile(file);
        }

    }

    public File getFile(String filename) throws IOException {

        File file = new File(rootPath.resolve(filename).toString());
        if (!file.exists()) {
            throw new IOException("File does not exist");
        } else {
            return file;
        }

    }

}
