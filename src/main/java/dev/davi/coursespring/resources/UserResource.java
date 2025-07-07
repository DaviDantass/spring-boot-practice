package dev.davi.coursespring.resources;

import dev.davi.coursespring.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> finAll(){
        User user = new User(1L, "Maria", "mariaconst@gmail.com", "8233-2323", "123ds233");
        return ResponseEntity.ok().body(user);
    }
}
