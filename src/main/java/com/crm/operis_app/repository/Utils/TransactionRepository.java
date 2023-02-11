package com.crm.operis_app.repository.Utils;

import com.crm.operis_app.model.Utils.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public Page<Transaction> findAllByOrderByIdDesc(Pageable pageable);

}
