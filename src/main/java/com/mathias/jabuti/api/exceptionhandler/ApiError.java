package com.mathias.jabuti.api.exceptionhandler;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiError {
	private Integer status;
	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
}
