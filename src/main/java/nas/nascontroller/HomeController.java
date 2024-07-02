package nas.nascontroller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return UserService.getData(authentication.getName());
    }

    @GetMapping("/upload")
    public ResponseEntity<Resource> upload() {

        Resource resource = new FileSystemResource(new File(
                "/Users/owner/Downloads/NAS-Controller/src/main/resources/static/upload.html"
        ));
        return ResponseEntity.ok().body(resource);

    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download() {

        Resource resource = new FileSystemResource(new File(
                "/Users/owner/Downloads/NAS-Controller/src/main/resources/static/download.html"
        ));
        return ResponseEntity
                .ok().body(resource);
    }

}
