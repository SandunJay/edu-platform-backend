package com.techie.microservices.orderservice.client;

import com.techie.microservices.orderservice.dto.UserCookieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service" , url = "http://localhost:8082")
public interface UserClient {

    @RequestMapping(method= RequestMethod.GET, value = "/api/v1/auth/check-user-validity-user")
    boolean checkUserValidity(@RequestParam("jwtToken") String jwtToken);

    @RequestMapping(method= RequestMethod.GET, value = "/api/v1/auth/access-user-cookie")
    UserCookieResponse accessUserCookie();
}
