package com.core.payment.gateway.payment_gateway.controller.core;

import com.core.payment.gateway.payment_gateway.common.dto.request.CreateBankRequestDTO;
import com.core.payment.gateway.payment_gateway.common.dto.response.GenericApiResponse;
import com.core.payment.gateway.payment_gateway.entity.Bank;
import com.core.payment.gateway.payment_gateway.service.core.transaction.bank.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/bank")
public class BankController {
    private final BankService bankService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> addBank(@Valid @RequestBody CreateBankRequestDTO request) {
        return new ResponseEntity<>(bankService.addBank(request), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> getAll(@Valid @RequestBody CreateBankRequestDTO request) {
        return new ResponseEntity<>(bankService.getAll(request), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> delete(@Valid @RequestBody CreateBankRequestDTO request) {
        return new ResponseEntity<>(bankService.delete(request), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> update(@Valid @RequestBody CreateBankRequestDTO request) {
        return new ResponseEntity<>(bankService.update(request), HttpStatus.OK);
    }

}
