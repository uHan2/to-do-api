package com.example.config.exception.types;

import lombok.Getter;

@Getter
public enum ErrorCode implements Result{

	// DEFAULT
	SUCCESS("00000", "success.msg", "성공하였습니다"),
	ERROR("99999", "error.msg", "오류가 발생했습니다"),
	FAIL("99998", "fail.msg", "실패하였습니다"),
	SERVICE_ERROR("99997", "service.error.msg", "오류가 발생했습니다"),

	// REQUEST, RESPONSE
	REQUEST_ERROR("98001", "request.error.msg", "요청을 처리하던 도중에 오류가 발생했습니다"),
	RESPONSE_ERROR("98002", "response.error.msg", "결과를 전송도중에 오류가 발생했습니다"),

	;
	private final String code;
	private final String messageKey;
	private String message;

	ErrorCode(String code, String messageKey, String message) {
		this.code = code;
		this.messageKey = messageKey;
		this.message = message;
	}

	public static ErrorCode serviceError(String message) {
		ErrorCode serviceError = SERVICE_ERROR;
		serviceError.message = message;

		return serviceError;
	}
}
