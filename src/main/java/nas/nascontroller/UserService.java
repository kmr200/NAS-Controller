package nas.nascontroller;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static String getData(String username) {
        if (username.equals("kamron")) {
            return "Hello, Kamron!";
        } else {
            return "Hello";
        }
    }

}
