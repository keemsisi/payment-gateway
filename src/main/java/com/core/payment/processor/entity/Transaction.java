package com.core.payment.processor.entity;

import com.core.payment.processor.common.dto.response.TransactionChannel;
import com.core.payment.processor.common.enums.TransactionStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction extends AbstractBaseEntity implements Serializable {
    private LocalDateTime dateCompleted;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(100) not null default 'NEW'")
    private TransactionStatus status;
    @Column(columnDefinition = "varchar(100) not null")
    private TransactionChannel channel;
    @NotNull(message = "Amount can't be null")
    @Column(columnDefinition = "numeric(19,2) not null")
    private BigDecimal amount;
    @Column(columnDefinition = "numeric(19,2) not null default 0")
    private BigDecimal fee;
    private String senderName;
    private String receiverName;
    private String senderAccountId;
    private String receiverAccountId;
    private String metaData;
    private String gatewayMeta;

}
