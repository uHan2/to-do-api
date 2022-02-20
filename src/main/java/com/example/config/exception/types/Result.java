package com.example.config.exception.types;

import java.io.Serializable;

public interface Result extends Serializable {

	String getCode();
	String getMessageKey();
	String getMessage();

}
