package com.crm.operis_app.services.server;

import com.crm.operis_app.exception.ResourceNotFoundException;
import com.crm.operis_app.model.server.ServerConfig;
import com.crm.operis_app.repository.server.ServerConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ServerConfigService {

    @Autowired
    ServerConfigRepository serverConfigRepository;


    public List<ServerConfig> getServerConfig() {
        return serverConfigRepository.findAll();
    }

    public Optional<ServerConfig> getServerConfigById(Long serverConfigId) {
        if (!serverConfigRepository.existsById(serverConfigId)) {
            throw new ResourceNotFoundException(" ServerConfig with id " + serverConfigId + " not found");
        }
        return serverConfigRepository.findById(serverConfigId);
    }

    public ServerConfig getServerConfigDecrypted(Long serverConfigId) {
        if (!serverConfigRepository.existsById(serverConfigId)) {
            throw new ResourceNotFoundException(" ServerConfig with id " + serverConfigId + " not found");
        }
        ServerConfig s = serverConfigRepository.findById(serverConfigId).get();
        String decoded = this.decode(s.getUsersNumber());
        ServerConfig serverConfig1 = new ServerConfig();
        serverConfig1.setId(s.getId());
        serverConfig1.setServerPort(s.getServerPort());
        serverConfig1.setEmail(s.getEmail());
        serverConfig1.setSmtp(s.getSmtp());
        serverConfig1.setTransportProtocol(s.getTransportProtocol());
        serverConfig1.setPassword(s.getPassword());
        serverConfig1.setSsl(s.getSsl());
        serverConfig1.setUsersNumber(decoded);
        serverConfig1.setNotification(s.getNotification());
        serverConfig1.setDelaiRappelNotification(s.getDelaiRappelNotification());
        serverConfig1.setDbName(s.getDbName());

        return serverConfig1;
    }


    public ServerConfig createServerConfig(ServerConfig serverConfig) {

        String coded = this.encode(serverConfig.getUsersNumber());
        serverConfig.setUsersNumber(coded);

        return serverConfigRepository.save(serverConfig);
    }

    public ServerConfig updateServerConfigById(Long serverConfigId, ServerConfig serverConfigRequest) {

        Optional<ServerConfig> serverConfig = serverConfigRepository.findById(serverConfigId);
        if (!serverConfigRepository.existsById(serverConfigId)) {
            throw new ResourceNotFoundException(" ServerConfig with id " + serverConfigId + " not found");
        }
        ServerConfig serverConfig1 = serverConfig.get();
        serverConfig1.setServerPort(serverConfigRequest.getServerPort());
        serverConfig1.setEmail(serverConfigRequest.getEmail());
        serverConfig1.setSmtp(serverConfigRequest.getSmtp());
        serverConfig1.setTransportProtocol(serverConfigRequest.getTransportProtocol());
        serverConfig1.setPassword(serverConfigRequest.getPassword());
        serverConfig1.setSsl(serverConfigRequest.getSsl());
        serverConfig1.setNotification(serverConfigRequest.getNotification());
        serverConfig1.setDelaiRappelNotification(serverConfigRequest.getDelaiRappelNotification());
        serverConfig1.setDbName(serverConfigRequest.getDbName());

        return serverConfigRepository.save(serverConfig1);
    }


    public ServerConfig updateUsersNumber(Long serverConfigId, String nb) {

        Optional<ServerConfig> serverConfig = serverConfigRepository.findById(serverConfigId);
        if (!serverConfigRepository.existsById(serverConfigId)) {
            throw new ResourceNotFoundException(" ServerConfig with id " + serverConfigId + " not found");
        }
        ServerConfig serverConfig1 = serverConfig.get();
        String coded = this.encode(nb);
        serverConfig1.setUsersNumber(coded);

        return serverConfigRepository.save(serverConfig1);
    }

    public ResponseEntity<Object> deleteServerConfigById(Long serverConfigId) {
        if (!serverConfigRepository.existsById(serverConfigId)) {
            throw new ResourceNotFoundException("ServerConfig with id " + serverConfigId + " not found");
        }
        serverConfigRepository.deleteById(serverConfigId);
        return ResponseEntity.ok().build();
    }


    public String encode(String number) {
        //  convert the Given Integer into String
        String encryptNum = "";
        //Scan each character of String, Read ASCII of that character and add it with secret key
        for (int i = 0; i < number.length(); i++) {
            int a = number.charAt(i);
            a += 148119;
            encryptNum += (char) a;
        }
        return encryptNum;
    }

    public String decode(String encryptText) {
        String decodeText = "";
        for (int i = 0; i < encryptText.length(); i++) {
            int a = (int) encryptText.charAt(i);
            a -= 148119;
            decodeText += (char) a;
        }
        String decodeId = decodeText;
        return decodeId;
    }

    /*
     */
}
