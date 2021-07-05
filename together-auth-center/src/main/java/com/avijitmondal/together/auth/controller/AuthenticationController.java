package com.avijitmondal.together.auth.controller;

import com.avijitmondal.together.auth.model.UserTokenSession;
import com.avijitmondal.together.auth.service.UserTokenSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@RestController
@Api(value = "Authentication API", description = "Authenticate user using authorization token.")
public class AuthenticationController {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    private final long tokenExpiryTime = 3600;

    @Autowired
    private UserTokenSessionService userTokenSessionService;


    @ApiOperation(value = "Authenticated User Login", response = UserTokenSession.class)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserTokenSession.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserTokenSession> login(@RequestHeader HttpHeaders httpHeaders, Principal principal, HttpServletRequest httpServletRequest) {

        String username = principal.getName();
        UserTokenSession userTokenSession = buildUserTokenSession(principal, httpHeaders);
        userTokenSession = userTokenSessionService.saveUserTokenSessionMapping(userTokenSession);

        logger.info("User " + username + " successfully logged in. User, Token and Session mapping stored." + userTokenSession);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Message", "Success");
        responseHeaders.add("Set-Cookie", userTokenSession.getSessionId());

        return new ResponseEntity<>(userTokenSession, responseHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "Validate the given token", response = UserTokenSession.class)
    @PostMapping(value = "/validateToken",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = UserTokenSession.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<UserTokenSession> validateToken(@RequestHeader HttpHeaders httpHeaders, Principal principal, HttpServletRequest httpServletRequest) {

        String username = principal.getName();
        UserTokenSession userTokenSession = buildUserTokenSession(principal, httpHeaders);

        ResponseEntity<UserTokenSession> responseEntity;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Set-Cookie", userTokenSession.getSessionId());

        UserTokenSessionService.ValidMappingResponse validMappingResponse = userTokenSessionService.isValidUserTokenSessionMapping(userTokenSession);
        if (validMappingResponse.isValid()) {

            logger.info("User " + username + " has valid token." + validMappingResponse.getUserTokenSession());
            responseHeaders.add("Message", "Valid Token");
            responseEntity = new ResponseEntity<UserTokenSession>(validMappingResponse.getUserTokenSession(), responseHeaders, HttpStatus.OK);

        } else {

            logger.info("User " + username + " has invalid token.");
            responseHeaders.add("Message", "Invalid Token");
            responseEntity = new ResponseEntity<>(userTokenSession, responseHeaders, HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }

    private UserTokenSession buildUserTokenSession(Principal principal, HttpHeaders httpHeaders) {

        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) ((OAuth2Authentication) principal).getDetails();
        String tokenValue = oAuth2AuthenticationDetails.getTokenValue();
        String username = principal.getName();
        String[] sessionId = new String[1];

        if (Objects.nonNull(httpHeaders.get("cookie"))) {
            sessionId = httpHeaders.get("cookie").get(0).split(";");
        } else {
            logger.info("User " + username + " cookie not found. JSessionId not set.");

            // Swagger2 does not support cookie for that putting default sessssion id.
            sessionId[0] = "JSEESION-ID";
        }

        return new UserTokenSession(username, tokenValue, sessionId[0], tokenExpiryTime);
    }
}
