package com.crm.operis_app.controller.authUser;

import com.crm.operis_app.dto.PasswordForgotDto;
import com.crm.operis_app.dto.PasswordResetDto;
import com.crm.operis_app.services.authUser.PasswordForgotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class PasswordForgotController {
	
	@Autowired
    PasswordForgotService passwordForgotService;

	@PostMapping("/forgotPassword/{domain}")
	public ResponseEntity<?> forgotPassword(@PathVariable(value = "domain") String domain, @RequestBody PasswordForgotDto dto, HttpServletRequest request)
	{
		return new ResponseEntity<>(passwordForgotService.forgotPasswordSendMail(dto, request,domain), HttpStatus.OK);
	}
	
	@GetMapping("resetPassword")
	public ResponseEntity<?>  getToken(@RequestParam("token") String token)
	{
		 return new ResponseEntity<>(passwordForgotService.checkToken(token),HttpStatus.OK);
	}
	
	@PutMapping("resetPass")
	public ResponseEntity<?> resetPass(@RequestBody PasswordResetDto passResetDto)
	{
		return new ResponseEntity<>(passwordForgotService.resetPass(passResetDto),HttpStatus.OK);
	}

}
