/* ***************************************************************************
 * FILE NAME   : RestService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Jun 27, 2017
 * DESCRIPTION : TogetherDesktop
 *************************************************************************** */
package com.avijitmondal.together.core.ws;

import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.data.cache.CentralCache;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.avijitmondal.together.core.dto.ErrorResponseDTO;
import com.avijitmondal.together.core.util.parser.GsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;

/**
 * @author avijit
 *
 */
public class RestService {

    private final Logger logger = LogManager.getLogger(RestService.class);
    private String url;
    private HttpMethod httpMethod;
    private Object requestBody;
    private boolean isSecured;
    private final CentralCache centralCache = CentralCache.getInstance();
    private HttpResponse httpResponse = null;

    public RestService(HttpMethod httpMethod, boolean isSecured, String url) {
        this.httpMethod = httpMethod;
        this.isSecured = isSecured;
        this.url = url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        logger.debug("setUrl");
        this.url = url;
    }

    /**
     * @param httpMethod the httpMethod to set
     */
    public void setHttpMethod(HttpMethod httpMethod) {
        logger.debug("setHttpMethod");
        this.httpMethod = httpMethod;
    }

    /**
     * @param requestBody the requestBody to set
     */
    public void setRequestBody(Object requestBody) {
        logger.debug("setRequestBody");
        this.requestBody = requestBody;
    }

    /**
     *
     * @param isSecured
     */
    public void isSecured(boolean isSecured) {
        logger.debug("setIsSecured");
        this.isSecured = isSecured;
    }

    /**
     *
     * @throws IOException
     */
    public void execute() throws IOException {
        logger.debug("execute");
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
                .setSocketTimeout(5000).build();

        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(GsonParser.toString(requestBody));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            logger.error(unsupportedEncodingException.getMessage());
        }

        HttpClientBuilder builder = HttpClientBuilder.create();

        CloseableHttpClient client = builder.build();

        switch (httpMethod) {
            case GET: {
                HttpGet request = new HttpGet(url);
                request.setConfig(requestConfig);
                request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

                if (isSecured) {
                    request.addHeader(Constants.AUTHORIZATION, centralCache.getAsString(Constants.AUTH_TOKEN));
                }

                httpResponse = client.execute(request);
            }
            break;
            case POST: {
                HttpPost request = new HttpPost(url);
                request.setEntity(stringEntity);
                request.setConfig(requestConfig);
                request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

                if (isSecured) {
                    request.addHeader(Constants.AUTHORIZATION, centralCache.getAsString(Constants.AUTH_TOKEN));
                }

                httpResponse = client.execute(request);
            }
            break;
            case PUT: {
                HttpPut request = new HttpPut(url);
                request.setEntity(stringEntity);
                request.setConfig(requestConfig);
                request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

                if (isSecured) {
                    request.addHeader(Constants.AUTHORIZATION, centralCache.getAsString(Constants.AUTH_TOKEN));
                }

                httpResponse = client.execute(request);
            }
            break;
            case DELETE: {
                HttpDelete request = new HttpDelete(url);
                request.setConfig(requestConfig);
                request.addHeader(HttpHeaders.ACCEPT, "application/json");
                request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

                if (isSecured) {
                    request.addHeader(Constants.AUTHORIZATION, centralCache.getAsString(Constants.AUTH_TOKEN));
                }

                httpResponse = client.execute(request);
            }
            break;
            default:
                break;
        }

    }

    public boolean isSuccessResponse(int expectedStatusCode) {
        return httpResponse.getStatusLine().getStatusCode() == expectedStatusCode;
    }

    public ErrorResponseDTO getErrorResponse() throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseAsString = EntityUtils.toString(httpEntity);
        return GsonParser.fromString(responseAsString, ErrorResponseDTO.class);
    }

    public String getSuccessResponse() throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity);
    }
}
