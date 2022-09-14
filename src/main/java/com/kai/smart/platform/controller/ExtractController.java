package com.kai.smart.platform.controller;

import com.kai.smart.platform.extract.*;
import com.kai.smart.platform.model.DefaultResponse;
import com.kai.smart.platform.model.ExtractRequest;
import com.kai.smart.platform.model.StatusEnum;
import com.kai.smart.platform.service.ExtractService;
import com.kai.smart.platform.util.ResponseUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

//import com.kai.smart.platform.util.ResponseUtils;

@Slf4j
@RestController
@Api("extract service")
public class ExtractController {

    @Autowired
    ExtractService extractService;

    @PostMapping("/extract")
    public ResponseEntity<DefaultResponse> extract(@RequestBody ExtractRequest extractRequest, HttpServletRequest request) {


    final Integer API_ID = 1101;
    String requestId = UUID.randomUUID().toString();
    /*
    TODO 저장위치 생성 로직 만들고 doExtract 호출시 파라메터로 던진다.
    사용자 디렉토리 + 날짜 + 추출 데이터명
    */
    if(extractRequest.equals("HIVE")){
        switch (extractRequest.getExtractType()){
            case "JOIN":
                extractService.doExtract(new JoinExtractor());
            break;
            case "SQL":
                extractService.doExtract(new HiveSqlExtractor());
            break;
            default:
                extractService.doExtract(new HiveExtractor());
        }

    }else if(extractRequest.equals("IMPALA")){
        switch (extractRequest.getExtractType()) {
            case "JOIN":
                extractService.doExtract(new JoinExtractor());
                break;
            case "SQL":
                extractService.doExtract(new ImpalaSqlExtractor());
                break;
            default:
                extractService.doExtract(new ImpalaExtractor());
        }
    }
    return ResponseUtils.getResponse(StatusEnum.SUCCESS,"Extract Request Success","");



}
}
