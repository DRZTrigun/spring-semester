package geek.rest;

import geek.controller.BadRequestException;
import geek.controller.NotFoundException;
import geek.service.UserRepr;
import geek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<UserRepr> findAll(){
        return userService.findAll().stream()
                .peek(u -> u.setPassword(null))
                .collect(Collectors.toList());
    }

    @GetMapping(path="/{id}")
    public UserRepr findById(@PathVariable("id") Long id){
        UserRepr userRepr = userService.findById(id)
                .orElseThrow(NotFoundException::new);
        userRepr.setPassword(null);
        return userRepr;
    }

    @PostMapping(consumes = "application/json")
    public UserRepr create(@RequestBody UserRepr userRepr){
        if (userRepr.getId() != null){
            throw new BadRequestException();
        }
        userService.save(userRepr);
        return userRepr;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody UserRepr userRepr){
        if (userRepr.getId() == null){
            throw new BadRequestException();
        }
        userService.save(userRepr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExecption(NotFoundException ex){
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestExecption(BadRequestException ex){
        return new ResponseEntity<>("Entity bad request", HttpStatus.BAD_REQUEST);
    }
}
