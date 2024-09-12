package com.core.payment.processor.controller.core;

import com.core.payment.processor.common.dto.request.bank.CreateBankRequestDTO;
import com.core.payment.processor.common.dto.response.GenericApiResponse;
import com.core.payment.processor.entity.Bank;
import com.core.payment.processor.service.core.transaction.bank.BankService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/api/bank")
public class BankController {
    private final BankService bankService;

    @PreAuthorize("hasAuthority('CREATE_BANK')")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> addBank(@Valid @RequestBody CreateBankRequestDTO request) {
        return new ResponseEntity<>(new GenericApiResponse<>(bankService.addBank(request), "Created successfully", 200), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Page<Bank>>> getAll(Pageable request) {
        return new ResponseEntity<>(new GenericApiResponse<>(bankService.getAll(request), "Fetched successfully", 200), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_BANK')")
    @RequestMapping(value = "/{bankId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericApiResponse<Bank>> delete(final @PathVariable Long bankId) {
        return new ResponseEntity<>(new GenericApiResponse<>(bankService.delete(bankId), "Deleted successfully", 200), HttpStatus.OK);
    }

}
