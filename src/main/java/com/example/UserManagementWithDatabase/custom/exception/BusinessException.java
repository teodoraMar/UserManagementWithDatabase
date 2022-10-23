package com.example.UserManagementWithDatabase.custom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException  {
    private String errorCode;
    private String errorMessage;




}
