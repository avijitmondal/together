/*****************************************************************************
 * FILE NAME   : UserController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.user.controller;

import com.avijit.together.core.data.I_Constant;
import com.avijit.together.core.dto.User;
import com.avijit.together.core.util.parser.GsonParser;
import com.avijit.together.core.ws.HttpMethod;
import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.ws.RestService;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = I_Constant.API_USERS)
public class UserController {

    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     *
     */
//    @Autowired
//    private UserService userService;

    /**
     * @param pageable
     * @return
     */
    @GetMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseDTO<List<User>> findAll(Pageable pageable) {
        RestService restService = new RestService();

        restService.setHttpMethod(HttpMethod.GET);
        restService.isSecured(false);
        restService.setUrl("http://127.0.0.1:8885/api/v1/users");

        try {
            HttpResponse httpResponse = restService.execute();
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseAsString = EntityUtils.toString(httpEntity);

                ResponseDTO<List<User>> response = (ResponseDTO<List<User>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<User>>>() {
                }.getType());

                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return new PageResource<>(null, "page", "size");
        return null;
    }
}
