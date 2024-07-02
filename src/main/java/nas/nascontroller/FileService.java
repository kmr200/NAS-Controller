package nas.nascontroller;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private final Path rootPath = Path.of("/Users/owner/Downloads/Temp");
    final Gson gson = new Gson();

    public void saveFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        } else {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootPath.resolve(file.getOriginalFilename()));
            }
        }
    }

    public void saveMultipleFiles(MultipartFile[] files) throws IOException {

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

    public String getFiles() {
        File folder = rootPath.toFile();

        File[] files = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    //skip Desktop Service Store
                    if (file.getName().equals(".DS_Store")) {
                        continue;
                    }
                    fileNames.add(file.getName());
                }
            }
        }
        return gson.toJson(fileNames);
    }
}


