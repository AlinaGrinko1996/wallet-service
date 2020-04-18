package ua.nure.hrynko.walletservice.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.hrynko.walletservice.SonarRequestSender;
import ua.nure.hrynko.walletservice.dto.SonarPayloadDTO;

@RestController
public class SonarController {
    @PostMapping("/sonar-receiver")
    public void addTransaction(@RequestBody @Validated SonarPayloadDTO sonarPayloadDTO) {
        SonarRequestSender.getInfoFromSonarScan(sonarPayloadDTO.getRevision(), sonarPayloadDTO.getProject().getKey());
        //return playerService.addTransaction(transactionDTO);
    }
}
