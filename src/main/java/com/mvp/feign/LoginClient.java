package com.mvp.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvp.model.AuthResponse;

//To make connection with the auth microservice and checks whether the user is valid
@FeignClient(url = "${login.feign.client}", name = "${login.feign.name}")
public interface LoginClient {
	@RequestMapping(path = "/validate", method = RequestMethod.GET)
	public ResponseEntity<AuthResponse> verifyToken(
			@RequestHeader(name = "Authorization", required = true) String token);

}