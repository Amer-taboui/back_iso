package com.crm.operis_app.services.authUser;


import com.crm.operis_app.dto.PasswordForgotDto;
import com.crm.operis_app.dto.PasswordResetDto;
import com.crm.operis_app.model.authUser.PasswordResetToken;
import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.repository.authUser.PasswordTokenRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordForgotService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordTokenRepository tokenRepository;

    @Autowired
    EmailServiceSend emailService;

    @Autowired
    PasswordEncoder encoder;


    public ResponseEntity<?> forgotPasswordSendMail(PasswordForgotDto passwordForgotDto, HttpServletRequest request, String domain) {
        Optional<User> user = userRepository.findByEmail(passwordForgotDto.getEmail());
        if (!user.isPresent()) {
            return new ResponseEntity<>("Nous n'avons pas pu trouver de compte avec cet E-mail", HttpStatus.NOT_FOUND);

        } else {
            User userInfo = user.get();
            PasswordResetToken token = new PasswordResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(userInfo);
            token.setExpiryDate(30);
            tokenRepository.save(token);
            //  String appUrl = request.getScheme() + "://" + request.getServerName();
            // String appUrl = "http://localhost:4200/pages/response-password-reset";
//             String appUrl =  request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort()+"/Genia/auth/reset-password";

            String appUrl = request.getScheme() + "://" + domain + "/Genia/auth/reset-password";
            //String appUrl = "http://localhost:4200/Genia/auth/reset-password";

            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();

            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(userInfo.getEmail());
            passwordResetEmail.setSubject("Demande de réinitialisation du mot de passe");
            passwordResetEmail.setText("Pour réinitialiser votre mot de passe, cliquez sur le lien ci-dessous:\n  " + appUrl
                    + "?token=" + token.getToken());

            emailService.sendEmail(passwordResetEmail);
            return new ResponseEntity<>("Demande de réinitialisation du mot de passe reçue. Vérifiez votre boîte de réception pour le lien de réinitialisation", HttpStatus.OK);
        }
    }


    public ResponseEntity<?> checkToken(String token) {
        Optional<PasswordResetToken> passResetToken = tokenRepository.findByToken(token);
        if (passResetToken.isPresent()) {
            PasswordResetToken passTokn = passResetToken.get();
            if (!passTokn.isExpired()) {
                return new ResponseEntity<>("Le lien est valide ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Expiration du temps", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Le lien est invalide !", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> resetPass(PasswordResetDto passResetDto) {
        Optional<PasswordResetToken> passResetToken = tokenRepository.findByToken(passResetDto.getToken());

        if (passResetToken.isPresent()) {
            PasswordResetToken passTokn = passResetToken.get();
            User user = passTokn.getUser();
            user.setPassword(encoder.encode(passResetDto.getNewPass()));
            if (!passResetDto.getNewPass().isEmpty() && !passTokn.isExpired()) {
                userRepository.save(user);
                return new ResponseEntity<>("Le mot de passe a été réinitialisé. Vous pouvez maintenant vous connecter avec les nouvelles informations d'identification", HttpStatus.OK);
            }
            return new ResponseEntity<>("Veuillez  saisir votre nouveau mot de passe ", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>("Erreur", HttpStatus.BAD_REQUEST);
        }
    }
}
