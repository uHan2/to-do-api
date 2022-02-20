package com.example.config.exception.model;

import com.example.config.exception.types.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

	private String messageKey;
	private String code;
	private String message;

	public ExceptionResponse(ErrorCode code) {
		this.message = code.getMessage();
		this.messageKey = code.getMessageKey();
		this.code = code.getCode();
	}

    public static ExceptionResponse convertServiceError(String message) {
        return new ExceptionResponse(ErrorCode.serviceError(message));
    }

	public static ExceptionResponse of(ErrorCode code) {
		return new ExceptionResponse(code);
	}
}
