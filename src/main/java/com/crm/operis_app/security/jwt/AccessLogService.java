package com.crm.operis_app.security.jwt;


import com.crm.operis_app.model.authUser.AccessLog;

/*
 * This UserAuthTokenService interface gives the list of all the service that exist in the userAuthToken service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface AccessLogService {

    void addAccessToken(long userId, String accessToken, String ipAdress);

    void removeAccessToken(String accessToken);

    AccessLog isUserLoggedIn(String accessToken);

    long getUserId(String accessToken);
}
