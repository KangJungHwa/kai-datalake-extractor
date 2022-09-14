package com.kai.smart.platform.model;

public enum StatusEnum {


    OK("200", "OK"),
    SUCCESS("0000", "정상"),
    TRAIN_FAIL("2000", "학습실패"),
    TRAIN_REQUEST_FAIL("2001", "학습 요청 실패"),
    DATA_NOT_FOUND("2002", "요청 데이터 없음"),
    ENGINE_NOT_FOUND("2003", "엔진 데이터 없음"),
    TRAIN_INSTANCE_CREATE_FAIL("2004", "학습 인스턴스 생성 실패"),
    SERVICE_INSTANCE_CREATE_FAIL("5107", "서비스 인스턴스 생성 실패"),
    MODEL_TRAIN_STOP_FAIL("2005", "모델 학습 중지 실패"),
    TRAIN_INSTANCE_DELETE_FAIL("2006", "학습 인스턴스 삭제 실패"),
    SERVICE_INSTANCE_DELETE_FAIL("2007", "서비스 인스턴스 삭제 실패"),
    SERVICE_REQUEST_FAIL("2008", "추론 서비스 호출 실패"),
    TRAIN_PARAMETER_SEARCH_FAIL("2009", "학습 파라메터조회 호출 실패"),
    STATUS_READ_FAIL("3000", "학습 상태조회 실패"),
    FILE_UPLOAD_FAIL("7103", "모델 업로드 실패"),
    BAD_REQUEST("400", "BAD_REQUEST"),
    NOT_FOUND("404", "NOT_FOUND"),
    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR"),
    EXPECTATION_FAILED("417", "EXPECTATION_FAILED"),
    UNAUTHORIZED("403", "UNAUTHORIZED");

    public String resultCode;
    public String result;

    StatusEnum(String resultCode, String result) {
        this.resultCode = resultCode;
        this.result = result;
    }

    @Override
    public String toString(){
        return resultCode;
    }

}
