package com.indra.demo;

import org.openapitools.api.CuentasApi;
import org.openapitools.model.Cuenta;
import org.openapitools.model.SolicitudDeposito;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class CuentaController implements CuentasApi {
    @Override
    public ResponseEntity<Void> depositToAccount(SolicitudDeposito solicitudDeposito) {
        return null;
    }

    @Override
    public ResponseEntity<Cuenta> getAccount() {
        Cuenta cuenta = new Cuenta();
        cuenta.setBalance(BigDecimal.ZERO);
        return ResponseEntity.ok(cuenta);
    }
}
