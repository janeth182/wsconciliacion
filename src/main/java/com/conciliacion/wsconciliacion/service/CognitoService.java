package com.conciliacion.wsconciliacion.service;

import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.conciliacion.wsconciliacion.configuration.AwsConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CognitoService {
	private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;
	
	public boolean verifyAccessToken(String accessToken) {
 		
		 GetUserRequest getUserRequest = new GetUserRequest().withAccessToken(accessToken);				 
		  try {
		        GetUserResult getUserResult = awsCognitoIdentityProvider.getUser(getUserRequest);
		        log.info("Usuario verificado: " + getUserResult.getUsername());
		        return true;
		    } catch (NotAuthorizedException e) {
		    	log.info(e.getMessage());
		    	log.info("El accessToken no es válido.", e.getMessage());
		        return false;
		    } catch (Exception e) {
		    	log.info("Ocurrió un error al verificar el accessToken.", e.getMessage());
		        return false;
		    }
	}
}
