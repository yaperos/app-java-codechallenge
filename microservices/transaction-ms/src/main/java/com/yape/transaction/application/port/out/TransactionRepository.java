package com.yape.transaction.application.port.out;

import com.yape.transaction.infra.out.adapter.data.model.TransactionDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionDataModel, Long> {
    Optional<TransactionDataModel> findOptionalByCode(UUID code);
    Optional<TransactionDataModel> findOptionalById(Long id);

    @Modifying
    @Query("update transaction t set t.status = :status, t.updateAt = :updateAt  where t.id = :id")
    void updateStatus(@Param(value = "id") Long id, @Param(value = "status") String status, @Param(value = "updateAt") LocalDateTime updateAt);
}


