package com.example.UserManagementWithDatabase.custom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControllerException extends RuntimeException{
    private String errorCode;
    private String errorMessage;


}
