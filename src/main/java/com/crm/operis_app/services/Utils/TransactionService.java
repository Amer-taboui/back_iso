package com.crm.operis_app.services.Utils;

import com.crm.operis_app.model.Utils.Transaction;
import com.crm.operis_app.repository.Utils.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    String[] apis = {"/api/auth/logout/", "/api/updateIpAdress/", "/api/changeLanguage/"};

    public static boolean substringExistsInArray(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }

    public void saveTransaction(HttpServletRequest request, String username, String ipAdress) {

        if (!request.getMethod().equals("GET")) {
            boolean apiExists = substringExistsInArray(request.getRequestURI(), apis);
            if (!apiExists) {
                Transaction transaction = new Transaction();
                transaction.setTypeTransaction(request.getMethod());
                transaction.setUsername(username);
                transaction.setValue(request.getRequestURI());
                transaction.setIpAdress(ipAdress);
                transactionRepository.save(transaction);
            }
        }
    }
}
