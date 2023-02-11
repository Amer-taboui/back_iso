package com.crm.operis_app.security.jwt;

import com.crm.operis_app.model.authUser.AccessLog;
import com.crm.operis_app.model.authUser.User;
import com.crm.operis_app.repository.authUser.AccessLogRepository;
import com.crm.operis_app.repository.authUser.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class  AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final UserRepository userRepository;

    public AccessLogServiceImpl(AccessLogRepository userAuthTokenRepository, UserRepository userRepository) {
        this.accessLogRepository = userAuthTokenRepository;
        this.userRepository = userRepository;
    }

    // This method is used to add access token details in the database
    @Override
    public void addAccessToken(long userId, String accessToken, String ipAdress) {
        Optional<User> user = userRepository.findById(userId);
        Date date = new Date();
        AccessLog userAuthToken = new AccessLog(user.get(), accessToken, date, ipAdress);
        accessLogRepository.save(userAuthToken);
    }

    // This method is used to remove access token from the database. By remove, it means making the access token no longer viable
    @Override
    public void removeAccessToken(String accessToken) {
        accessLogRepository.removeAuthToken(accessToken);
    }

    // This method is used to check whether the user is logged in or not
    @Override
    public AccessLog isUserLoggedIn(String accessToken) {
        return accessLogRepository.isUserLoggedIn(accessToken);
    }

    @Override
    public long getUserId(String accessToken) {
        return accessLogRepository.getUserId(accessToken);
    }
}
