package com.yape.transactionmanagement.repository;

import com.yape.transactionmanagement.model.entity.TransactionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT * FROM public.find_all_transactions_sp()", nativeQuery = true)
    List<TransactionEntity> callGetTransactionsSP();


    @Query(value = "SELECT * FROM public.find_transaction_by_code_sp(:txCode)", nativeQuery = true)
    TransactionEntity callGetTransactionByCode(@Param("txCode") String txCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE public.transaction SET status = :txState, updated_at = CURRENT_TIMESTAMP WHERE id = :txId", nativeQuery = true)
    void updateTransactionState(@Param("txId") Long transactionId,
                                @Param("txState") String transactionState);

}
