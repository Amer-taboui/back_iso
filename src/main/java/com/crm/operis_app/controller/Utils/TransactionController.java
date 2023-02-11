package com.crm.operis_app.controller.Utils;

import com.crm.operis_app.model.Utils.Transaction;
import com.crm.operis_app.repository.Utils.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transaction")
    public ResponseEntity<Map<String, Object>> getAllTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size
    ) {
        try {
            List<Transaction> transactions = new ArrayList<Transaction>();
            Pageable paging = PageRequest.of(page, size);

            Page<Transaction> pageTransaction;
            pageTransaction = transactionRepository.findAllByOrderByIdDesc(paging);

            transactions = pageTransaction.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("transactions", transactions);
            response.put("currentPage", pageTransaction.getNumber());
            response.put("totalItems", pageTransaction.getTotalElements());
            response.put("totalPages", pageTransaction.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //TEST
    /*
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getTest")
    public Object[] getTestt()
    {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://41.230.60.235:8080/api/amdecs", Object[].class);
        Object[] objects = responseEntity.getBody();
        return objects;

    }

    @PostMapping(value = "/postTest",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Test postTest(@RequestBody Test test)
    {
        RestTemplate restTemplate = new RestTemplate();

        Test response = restTemplate.postForObject("http://41.230.60.235:8080/api/IATF/iatf", test, Test.class);


        return response;

    }


    RequestCallback requestCallback(final Test updatedInstance) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        };

    }

    @PutMapping(value = "/putTest",consumes = MediaType.APPLICATION_JSON_VALUE)
    public   Test putTest(@RequestBody Test test)
    {
        Test updatedInstance = new Test();
        updatedInstance.setChapitre_iatf(test.getChapitre_iatf());
        updatedInstance.setExtrait(test.getExtrait());
        updatedInstance.setPoint_v(test.getPoint_v());
        updatedInstance.setSynth_iatf(test.getSynth_iatf());
        updatedInstance.setSynth_iso(test.getSynth_iso());
        updatedInstance.setCommentaire(test.getCommentaire());
        String resourceUrl ="http://41.230.60.235:8080/api/IATF/iatf/11";

        return  restTemplate.execute(
                resourceUrl,
                HttpMethod.PUT,
                requestCallback(updatedInstance),
                clientHttpResponse -> null);


    }



    @DeleteMapping(value = "/deleteTest")
    public   ResponseEntity<Object> deleteTest()
    {
         restTemplate.delete("http://41.230.60.235:8080/api/IATF/iatf/10");
        return ResponseEntity.ok().build();

    }
*/
}
