package com.crm.operis_app.controller.authUser;


import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.message.request.LoginForm;
import com.crm.operis_app.message.request.SignUpForm;
import com.crm.operis_app.message.response.JwtResponse;
import com.crm.operis_app.model.authUser.AccessLog;
import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.repository.authUser.AccessLogRepository;
import com.crm.operis_app.repository.authUser.RoleRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import com.crm.operis_app.security.jwt.AccessLogService;
import com.crm.operis_app.security.jwt.JwtProvider;
import com.crm.operis_app.services.GRH.PersonnelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.*;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonnelServiceImp personnelServiceImp;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private AccessLogService accessLogService;

    @Autowired
    AccessLogRepository accessLogRepository;

    @GetMapping("/accessLog")
    public ResponseEntity<Map<String, Object>> getAllUserAuth(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<AccessLog> accessLogs = new ArrayList<AccessLog>();
            Pageable paging  = PageRequest.of(page, size);

            Page<AccessLog> pageAccessLog;
            pageAccessLog = accessLogRepository.findAllByOrderByIdDesc(paging );

            accessLogs=pageAccessLog.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("accessLogs", accessLogs);
            response.put("currentPage", pageAccessLog.getNumber());
            response.put("totalItems", pageAccessLog.getTotalElements());
            response.put("totalPages", pageAccessLog.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @PostMapping("/signin/{ipAdress}")
    public ResponseEntity<?> authenticateUser(@PathVariable("ipAdress") String ipAdress, @Valid @RequestBody LoginForm loginRequest, HttpServletRequest request) throws UnknownHostException {
        String localHostAddress = "";

        if (request != null) {
            localHostAddress = request.getHeader("X-FORWARDED-FOR");
            if (localHostAddress == null || "".equals(localHostAddress)) {
                localHostAddress = request.getRemoteAddr();
            }
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        String language  =user.get().getLanguage();
        boolean blocked  =user.get().getBlocked();
        Long personnelId  =user.get().getPersonnel().getId();
        Long userId  =user.get().getId();
        User _user = user.get();
        _user.setCurrentIpAdress(localHostAddress);
        userRepository.save(_user);
        if(user.get().getActive()) {
            String jwt = jwtProvider.generateJwtToken(authentication);

            // amer
            accessLogService.addAccessToken(userId, jwt, localHostAddress);
            HttpHeaders headers = new HttpHeaders();
            headers.add("access-token", jwt);
            List<String> header = new ArrayList<>();
            header.add("access-token");
            //
            headers.setAccessControlExposeHeaders(header);
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), personnelId, language ,blocked, userDetails.getAuthorities()));
        }else{
            throw new ResourceNotFoundException("username est déjà utilisé");

        }

    }

    @PostMapping("/signup/personnel/{personnelId}")
    public User registerUser(@PathVariable("personnelId") Long personnelId,@Valid @RequestBody SignUpForm signUpRequest) {

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResourceNotFoundException("username est déjà utilisé");
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResourceNotFoundException("L'email est déjà utilisé");
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        User user1= userRepository.save(user);

        Long userId= user1.getId();
        personnelServiceImp.addPersonnelToUser(userId,personnelId);

        return user1;
    }
    //amer
    @PutMapping("/logout/{accessToken}")
    public ResponseEntity<String> logout(@PathVariable("accessToken") String accessToken){
        if(accessLogService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(accessLogService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            accessLogService.removeAccessToken(accessToken);
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);}
    }




       /*    @Autowired
        @Qualifier("sessionRegistry")
        private SessionRegistry sessionRegistry;

        @GetMapping(value = "/numberUsers")
        public List<String> numberUsers() {

            List<Object> principals = sessionRegistry.getAllPrincipals();

            List<String> usersNamesList = new ArrayList<>();

            for (Object principal: principals) {
                if (principal instanceof UserPrinciple) {
                    if (!usersNamesList.contains(((UserPrinciple) principal).getUsername())&& !((UserPrinciple) principal).getBlocked()) {
                        usersNamesList.add(((UserPrinciple) principal).getUsername());
                    }
                }
            }
            return usersNamesList;
        }




        @GetMapping(value = "/numberUser")
        public List<UserDetails> numberUser() {
            List<UserDetails> usersNamesList = new ArrayList<>();

            usersNamesList = sessionRegistry.getAllPrincipals()
                    .stream()
                    .filter(principal -> principal instanceof UserDetails)
                    .map(UserDetails.class::cast)
                    .collect(Collectors.toList());


            return usersNamesList;

        }*/
}