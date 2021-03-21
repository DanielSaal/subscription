package com.dsa.subscription.controller;

import com.dsa.subscription.controller.dto.RequestSubscriptionDTO;
import com.dsa.subscription.service.SubscriptionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @ApiOperation(value = "Registra uma assinatura", response = HttpStatus.class)
    @ApiResponses({@ApiResponse(code = 204, message = "Assinatura recebida com sucesso"),
            @ApiResponse(code = 400, message = "Dados da requisição inválidos"),
            @ApiResponse(code = 500, message = "Erro ao cadastrar assinatura")})
    @PostMapping
    public ResponseEntity<HttpStatus> register(@Valid @RequestBody RequestSubscriptionDTO requestSubscriptionDTO) {

        log.info("Assinatura recebida: {}", requestSubscriptionDTO);

        subscriptionService.send(requestSubscriptionDTO);

        return ResponseEntity.noContent().build();
    }
}
