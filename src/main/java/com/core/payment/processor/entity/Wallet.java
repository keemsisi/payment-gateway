package com.core.payment.processor.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet", indexes = {
        @Index(name = "ix_wallet_owner_id_account_number_uq", columnList = "owner_id,account_number", unique = true),
        @Index(name = "ix_wallet_reference_uq", columnList = "reference", unique = true),
        @Index(name = "ix_wallet_account_name_uq", columnList = "account_name"),
        @Index(name = "ix_wallet_account_number_uq", columnList = "account_number", unique = true)
})
public class Wallet extends AbstractBaseEntity implements Serializable {
    @Column(name = "owner_id")
    private Long ownerId;
    private String reference;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(columnDefinition = "varchar(100) default 'PRIMARY'")
    private WalletType walletType;
    private BigDecimal balanceAfter;
    private BigDecimal balanceBefore;

    @PrePersist
    public void onCreate() {
        this.setDateCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate() {
        this.setDateUpdated(LocalDateTime.now());
    }

    public enum WalletType {
        PRIMARY, SECONDARY
    }
}