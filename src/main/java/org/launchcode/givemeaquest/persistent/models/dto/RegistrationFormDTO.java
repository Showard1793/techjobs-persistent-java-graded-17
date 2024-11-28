package org.launchcode.givemeaquest.persistent.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO{

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void  setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
