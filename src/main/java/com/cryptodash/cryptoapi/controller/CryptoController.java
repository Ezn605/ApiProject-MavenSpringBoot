package com.cryptodash.cryptoapi.controller;

import com.cryptodash.cryptoapi.service.CryptoService;
import com.cryptodash.cryptoapi.model.CryptoInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/crypto-info")
    public Mono<ResponseEntity<CryptoInfoResponse>> getCryptoInfo(
            @RequestParam String coin,
            @RequestParam(required = false) Double investmentAmount) {
        
        return cryptoService.getCryptoInfo(coin, investmentAmount)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
}
