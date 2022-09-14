package com.kai.smart.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractRequest {

    /**
     * 추출 데이터 명
     */
    @ApiModelProperty(name = "dataSetName", value = "추출 데이터 명")
    @JsonProperty("dataSetName")
    String dataSetName;

    /**
     * db 종류 : HIVE, IMPALA
     */
    @ApiModelProperty(name = "dbType", value = "db 종류")
    @JsonProperty("dbType")
    String dbType;

    /**
     * 추출 타입: SQLGEN, JOIN, SQL
     */
    @ApiModelProperty(name = "extractType", value = "추출 타입")
    @JsonProperty("extractType")
    String extractType;

    /**
     * sql문
     */
    @ApiModelProperty(name = "sqlText", value = "sql 문")
    @JsonProperty("sqlText")
    String sqlText;

    @ApiModelProperty(name = "selectColumn", value = "selectColumn")
    @JsonProperty("selectColumn")
    List<String> selectColumn;

    @ApiModelProperty(name = "fromTable", value = "fromTable")
    @JsonProperty("fromTable")
    String fromTable;

    @ApiModelProperty(name = "whereFrom", value = "whereFrom")
    @JsonProperty("whereFrom")
    Condition whereFrom;

    @ApiModelProperty(name = "whereTo", value = "whereTo")
    @JsonProperty("whereTo")
    Condition whereTo;

    @ApiModelProperty(name = "whereIn", value = "whereIn")
    @JsonProperty("whereIn")
    List<Condition> whereIn;

    @ApiModelProperty(name = "whereEqual", value = "whereEqual")
    @JsonProperty("whereEqual")
    List<Condition> whereEqual;
}
