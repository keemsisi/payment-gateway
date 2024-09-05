package com.core.payment.gateway.payment_gateway.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet", indexes = {
        @Index(name = "ix_wallet_owner_id_uq", columnList = "owner_id", unique = true),
        @Index(name = "ix_wallet_reference_uq", columnList = "reference", unique = true),
        @Index(name = "ix_wallet_account_name_uq", columnList = "account_name")
})
public class Wallet extends AbstractBaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(100) default 'PRIMARY'")
    private WalletType walletType;
    private String accountName;
    private Long ownerId;
    private String reference;

    public enum WalletType {
        PRIMARY, SECONDARY
    }
}