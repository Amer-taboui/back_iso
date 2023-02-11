package com.crm.operis_app.controller.server;

import com.crm.operis_app.model.server.ServerConfig;
import com.crm.operis_app.services.server.ServerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ServerConfigController {

    @Autowired
    ServerConfigService serverConfigService;


    @RequestMapping(value = "/crypt/{data}", method = RequestMethod.GET)
    public String crypt(@PathVariable(value = "data") String data) {
        return serverConfigService.encode(data);
    }

    @RequestMapping(value = "/decrypt/{data}", method = RequestMethod.GET)
    public String decrypt(@PathVariable(value = "data") String data) {
        return serverConfigService.decode(data);
    }


    @RequestMapping(value = "/createServerConfig", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServerConfig createServerConfig(@RequestBody ServerConfig serverConfig) {
        return serverConfigService.createServerConfig(serverConfig);
    }

    @RequestMapping(value = "/serverConfig/{serverConfigId}", method = RequestMethod.GET)
    public Optional<ServerConfig> getServerConfigById(@PathVariable(value = "serverConfigId") Long serverConfigId) {
        return serverConfigService.getServerConfigById(serverConfigId);
    }

    @RequestMapping(value = "/serverConfigDecrypted/{serverConfigId}", method = RequestMethod.GET)
    public ServerConfig getServerConfigDecrypted(@PathVariable(value = "serverConfigId") Long serverConfigId) {
        return serverConfigService.getServerConfigDecrypted(serverConfigId);
    }

    @RequestMapping(value = "/serverConfig/{serverConfigId}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServerConfig updateServerConfig(@PathVariable(value = "serverConfigId") Long serverConfigId, @RequestBody ServerConfig serverConfig) {
        return serverConfigService.updateServerConfigById(serverConfigId, serverConfig);
    }

    /////////////////////////////////////////updateUsersNumber
    @RequestMapping(value = "/updateUsersNumber/{serverConfigId}/{usersNumber}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServerConfig updateUsersNumber(@PathVariable(value = "serverConfigId") Long serverConfigId, @PathVariable(value = "usersNumber") String usersNumber) {
        return serverConfigService.updateUsersNumber(serverConfigId, usersNumber);
    }//////////////////////////////////////////////

    @RequestMapping(value = "/serverConfig/{serverConfigId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteServerConfigById(@PathVariable(value = "serverConfigId") Long serverConfigId) {
        return serverConfigService.deleteServerConfigById(serverConfigId);
    }

}
