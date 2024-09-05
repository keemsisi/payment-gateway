package com.core.payment.gateway.payment_gateway.controller.core;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateWalletDTO;
import com.core.payment.gateway.payment_gateway.common.dto.request.WalletUpdateRequestDTO;
import com.core.payment.gateway.payment_gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.payment_gateway.common.enums.ResponseCodeMapping;
import com.core.payment.gateway.payment_gateway.entity.Wallet;
import com.core.payment.gateway.payment_gateway.service.core.transaction.wallet.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/wallet")
public class WalletController {
    private final WalletService walletService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Wallet>> createWallet(@Valid @RequestBody CreateWalletDTO request) {
        final var wallet = walletService.createWallet(request);
        return new ResponseEntity<>(new GenericApiResponse<>(wallet, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Wallet>> getById(@PathVariable(value = "walletId") Long walletId) {
        final var wallet = walletService.getWalletById(walletId);
        return new ResponseEntity<>(new GenericApiResponse<>(wallet, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(value = "/{walletId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Wallet>> delete(@PathVariable(value = "walletId") Long walletId) {
        final var wallet = walletService.deleteWalletById(walletId);
        return new ResponseEntity<>(new GenericApiResponse<>(wallet, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Wallet>> update(@Valid @RequestBody WalletUpdateRequestDTO request) {
        final var wallet = walletService.updateWallet(request);
        return new ResponseEntity<>(new GenericApiResponse<>(wallet, ResponseCodeMapping.OK.getMessage(),
                ResponseCodeMapping.OK.getCode(), true), HttpStatus.OK);
    }
}
