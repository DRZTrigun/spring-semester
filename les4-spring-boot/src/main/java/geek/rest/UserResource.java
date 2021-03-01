package geek.rest;

import geek.controller.BadRequestException;
import geek.controller.NotFoundException;
import geek.service.UserRepr;
import geek.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name =  "User resource API", description = "API to manipulate User resource ...")
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

    @GetMapping("filter")
    public Page<UserRepr> listPage(
            @RequestParam("usernameFilter")Optional<String> usernameFilter,
            @RequestParam("ageMinFilter") Optional<Integer> ageMinFilter,
            @RequestParam("ageMaxFilter") Optional<Integer> ageMiaxFilter,
            @Parameter(example = "1") @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortFiled") Optional<String> sortField ){
        return userService.findWithFilter(
                usernameFilter.orElse(null),
                ageMinFilter.orElse(null),
                ageMiaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(4),
                sortField.orElse(null));
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
