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
public class Condition {


    /**
     * 조건절 컬럼
     */
    @ApiModelProperty(name = "column", value = "조건절 컬럼")
    @JsonProperty("column")
    private String column;

    /**
     * 조건절 컬럼 타입
     */
    @ApiModelProperty(name = "type", value = "조건절 컬럼 타입")
    @JsonProperty("type")
    private String type;

    /**
     * value
     */
    @ApiModelProperty(name = "data", value = "조건절 컬럼 값")
    @JsonProperty("value")
    private Object value;
}
