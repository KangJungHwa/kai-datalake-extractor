package com.kai.smart.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DefaultResponse {

    /**
     * resultCode
     */
    @ApiModelProperty(name = "resultCode", value = "resultCode")
    @JsonProperty("resultCode")
    private String resultCode;

    /**
     * result 메세지
     */
    @ApiModelProperty(name = "result", value = "result")
    @JsonProperty("result")
    private String result;

    /**
     * 리턴 데이터
     */
    @ApiModelProperty(name = "data", value = "리턴 데이터")
    @JsonProperty("data")
    private Object data;

    /**
     * 추출 경로
     */
    @ApiModelProperty(name = "path", value = "추출 경로")
    @JsonProperty("path")
    private String path;

}
