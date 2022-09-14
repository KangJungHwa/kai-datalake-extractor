package com.kai.smart.platform.util;

import com.kai.smart.platform.model.DefaultResponse;
import com.kai.smart.platform.model.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

public class ResponseUtils {

    public static ResponseEntity<DefaultResponse> getResponse(StatusEnum statusEnum, String message, Object data) {

        DefaultResponse response = new DefaultResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        response.setResultCode(statusEnum.resultCode);
        response.setResult(statusEnum.result);
        //response.setResultCode(message);
        response.setData(data);
        HttpStatus httpStatus=getStatusEnum(statusEnum);
        return new ResponseEntity<>(response, headers, httpStatus);
    }

//    public static ResponseEntity<DefaultResponse> getResponse(StatusEnum statusEnum,String message,String instanceId, Object data, String instancetype) {
//
//        DefaultResponse response = new DefaultResponse();
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        response.setResultCode(statusEnum.resultCode);
//        response.setResult(statusEnum.result);
//        //response.setMessage(message);
//        if(instancetype.equals("TRAIN")) {
//            response.setTrainInstanceId(instanceId);
//        }else{
//            response.setServiceInstanceId(instanceId);
//        }
//        response.setData(data);
//        HttpStatus httpStatus=getStatusEnum(statusEnum);
//        return new ResponseEntity<>(response, headers, httpStatus);
//    }
//    public static ResponseEntity<DefaultResponse> getResponse(StatusEnum statusEnum,String message, String experimentId, Object data) {
//
//        DefaultResponse response = new DefaultResponse();
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        response.setResultCode(statusEnum.resultCode);
//        response.setResult(statusEnum.result);
//        //response.setResult(statusEnum);
//        //response.setMessage(message);
//        response.setExperimentId(experimentId);
//        response.setData(data);
//        HttpStatus httpStatus=getStatusEnum(statusEnum);
//        return new ResponseEntity<>(response, headers, httpStatus);
//    }

    public static HttpStatus getStatusEnum(StatusEnum statusEnum){
        HttpStatus httpStatus=null;
        switch (statusEnum){
            case OK :
            case SUCCESS:
                httpStatus = HttpStatus.OK;
                break;
            case BAD_REQUEST :
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case NOT_FOUND :
            case DATA_NOT_FOUND :
            case ENGINE_NOT_FOUND:
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case TRAIN_FAIL:
            case STATUS_READ_FAIL:

            case INTERNAL_SERVER_ERROR:
            case FILE_UPLOAD_FAIL:
            case TRAIN_INSTANCE_CREATE_FAIL:
            case SERVICE_INSTANCE_CREATE_FAIL:
            case TRAIN_INSTANCE_DELETE_FAIL:
            case SERVICE_INSTANCE_DELETE_FAIL:
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                httpStatus = HttpStatus.EXPECTATION_FAILED;
        }
        return httpStatus;
    }
}
