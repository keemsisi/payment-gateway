package com.core.payment.gateway.payment_gateway.entity;

import com.core.payment.gateway.payment_gateway.common.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction extends AbstractBaseEntity implements Serializable {
    private LocalDateTime dateCompleted;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(100) default 'NEW'")
    private TransactionStatus status;
}
