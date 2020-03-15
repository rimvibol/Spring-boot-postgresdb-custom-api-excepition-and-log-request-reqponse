package com.rvb.springwithapectlog.config;

import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestExceptionHandlerTest.class }, loader = AnnotationConfigContextLoader.class)
public class RestExceptionHandlerTest {

//    @Test
//    public void handleHttpMediaTypeNotSupported() {
//        Response response = givenAuth().body("").post(URL_PREFIX + "/api/foos");
//        AppException error = response.as(ApiError.class);
//
//        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error.get());
//        assertEquals(1, error.getErrors().size());
//        assertTrue(error.getErrors().get(0).contains("media type is not supported"));
//
//    }


}