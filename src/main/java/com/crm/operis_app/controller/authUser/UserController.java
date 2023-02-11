package com.crm.operis_app.controller.authUser;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.authUser.Role;
import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.repository.authUser.RoleRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import com.crm.operis_app.security.jwt.AccessLogService;
import com.crm.operis_app.security.jwt.JwtProvider;
import com.crm.operis_app.services.GRH.PersonnelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


 import java.math.BigInteger;
  import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PersonnelServiceImp personnelServiceImp;
    @Autowired
    private AccessLogService accessLogService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;



    /*  @GetMapping("/users")
      public List<User> getAllUsers() {
          return userRepository.findAll();
      }

  */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllPersonnel() {
        return userRepository.findByActiveIsTrue();
    }


    @RequestMapping(value = "/archivedUsers", method = RequestMethod.GET)
    public List<User> getArchivedListUsers() {
        return userRepository.findByActiveIsFalse();
    }


    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users/personnel")
    public List<BigInteger> findUserPersonnelId() {
        return userRepository.findUserPersonnelId();
    }



    @DeleteMapping("/users/{id}/{isDelete}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long userId,@PathVariable("isDelete") Boolean isDelete) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new ResourceNotFoundException("User with id " + userId + " not found");
            }
            User user1 = user.get();
            user1.setActive(isDelete);

            userRepository.save(user1);

            //    userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

//        @PostMapping("/createUser")
//        public ResponseEntity<String> createUser(@Valid @RequestBody SignUpForm signUpRequest) {
//            if(userRepository.existsByUsername(signUpRequest.getUsername())) {
//                return new ResponseEntity<String>("Échec -> username est déjà utilisé",
//                        HttpStatus.BAD_REQUEST);
//            }
//
//            if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//                return new ResponseEntity<String>("Échec -> L'email est déjà utilisé!",
//                        HttpStatus.BAD_REQUEST);
//            }
//
//            User user = new User( signUpRequest.getUsername(),
//                    signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
//
//            userRepository.save(user);
//
//            return ResponseEntity.ok().body("L'utilisateur s'est enregistré avec succès!");
//        }
//

    //    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/user/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> userData = userRepository.findByUsername(username);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping ("/user/{id}/personnel/{personnelId}")
    public ResponseEntity<User> updateUserByID(@PathVariable("id") Long id,@PathVariable("personnelId") Long personnelId, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            _user.setBlocked(user.getBlocked());
            personnelServiceImp.addPersonnelToUser(_user.getId(),personnelId);

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping ("/currentUser/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        Optional<User> userData = userRepository.findByUsername(username);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping ("/changePassword/{username}")
    public ResponseEntity<User> updatePassword(@PathVariable("username") String username, @RequestBody User user) {
        Optional<User> userData = userRepository.findByUsername(username);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setPassword(encoder.encode(user.getPassword()));
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping ("/changeLanguage/{id}")
    public ResponseEntity<User> updateLanguage(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setLanguage(user.getLanguage());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping ("/updateIpAdress/{id}/{ipAdress}")
    public ResponseEntity<User> updateIpAdress(@PathVariable("id") Long id,@PathVariable("ipAdress") String ipAdress) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setCurrentIpAdress(ipAdress);
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //
    @PostMapping ("checkPassword/{username}")
    public boolean checkPassword(@PathVariable("username") String username, @RequestBody String oldPassword) {
        Optional<User> userData = userRepository.findByUsername(username);
        User _user = userData.get();
        String  dbPassword = _user.getPassword();

        return encoder.matches(encoder.encode(oldPassword),dbPassword );
    }


    @RequestMapping(value = "/addRole/{roleId}/User/{userId}", method = RequestMethod.POST)

    public void addUserRoles( @PathVariable("roleId") Long roleId,@PathVariable("userId") Long userId  ) {

        Optional<Role> roleById = roleRepository.findById(roleId);
        if (!roleById.isPresent()) {
            throw new ResourceNotFoundException("role with id " + roleId + " does not exist");
        }
        Role role = roleById.get();

        Optional<User> userData = userRepository.findById(userId);
        if (!userData.isPresent()) {
            throw new ResourceNotFoundException("User with id " + userId + " does not exist");
        }
        User user = userData.get();

        user.getRoles().add(role);
        userRepository.save(user);

    }
    @RequestMapping(value = "/removeRole/{roleId}/User/{userId}", method = RequestMethod.POST)
    public void removeUserRoles( @PathVariable("roleId") Long roleId,@PathVariable("userId") Long userId  ) {

        Optional<Role> roleById = roleRepository.findById(roleId);
        if (!roleById.isPresent()) {
            throw new ResourceNotFoundException("role with id " + roleId + " does not exist");
        }
        Role role = roleById.get();

        Optional<User> userData = userRepository.findById(userId);
        if (!userData.isPresent()) {
            throw new ResourceNotFoundException("User with id " + userId + " does not exist");
        }
        User user = userData.get();

        user.getRoles().remove(role);
        userRepository.save(user);

    }




}





