
package com.example.demo2.api.v1.local.proyecto1.auth;

import com.example.demo2.api.v1.local.Utils.ResponseLocal;
import com.example.demo2.api.v1.local.Utils.UtilsLocal;
import com.example.demo2.api.v1.local.proyecto1.logs.LogService;
import com.example.demo2.api.v1.local.proyecto1.auth.login.LoginDto;
import com.example.demo2.api.v1.local.proyecto1.users.adapters.UserDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/local/auth")
@CrossOrigin
public class AuthController {
    
    @Autowired
    LogService logService;
    
    @Autowired
    AuthService authService;
    
    
    private String myClassName = AuthController.class.getName();
    
    @PostMapping("/register")
    public ResponseEntity<?> register (
        @Valid @RequestBody UserDto userDto, 
        BindingResult bindingResult,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                userDto.toString(), 
                req
            );
            return new ResponseEntity<Object>( response, HttpStatus.BAD_REQUEST );
        }
        
        try {
            Object resRegister = this.authService.register(userDto);
            HttpStatus httpStatus = response.validateService( resRegister, 
                "Usuario registrado correctamente",
                this.myClassName, 
                userDto.toString(), 
                req
            );
            return new ResponseEntity( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo registrar el usuario", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                userDto.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login (
        @Valid @RequestBody LoginDto login, 
        BindingResult bindingResult,
        HttpServletRequest req
    )
    {
        ResponseLocal response = new ResponseLocal( logService );
        if( bindingResult.hasErrors() ){
            response.setError( HttpStatus.BAD_REQUEST.value(), "", "", 
                bindingResult.getAllErrors(),
                this.myClassName, 
                login.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
        
        try {
            Object resRegister = this.authService.login(login);
            HttpStatus httpStatus = response.validateService( resRegister, 
                "Usuario ok",
                this.myClassName, 
                login.toString(), 
                req
            );
            return new ResponseEntity( response, httpStatus );
        }
        catch (Exception e) {
            response.setError( HttpStatus.BAD_REQUEST.value(), 
                "No se pudo comprobar el usuario", 
                e.getMessage(), 
                UtilsLocal.emptyErrorList(),
                this.myClassName, 
                login.toString(), 
                req
            );
            return new ResponseEntity<>( response, HttpStatus.BAD_REQUEST );
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> test ( )
    {
        String message = "Get del Auth/test = OK ";
        return new ResponseEntity( message, HttpStatus.OK );
    }
    
}
