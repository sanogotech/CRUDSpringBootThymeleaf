package com.starterkit.springboot.brs.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.starterkit.springboot.brs.controller.v1.request.UserSignupRequest;

@FeignClient(name = "simple-client", url = "http://localhost:8080/api")
@Service
public interface MyFeinClient {

	
	@PostMapping("/v1/user/signup")
    void  signUp(@RequestBody UserSignupRequest userSignupRequest);
	
	/*
	 @GetMapping("/data/{dataId}")
	    SimpleClientData getData(@PathVariable int dataId);
	    
	 */
	
	/*
	@PostMapping("/search")
	public Page<HelloDto> searchHellos(@RequestHeader("apiKey") String apiKey, 
	                                   @RequestBody HelloDto example,
	                                   @SpringQueryMap Pageable pageable);
	                                   */
}
