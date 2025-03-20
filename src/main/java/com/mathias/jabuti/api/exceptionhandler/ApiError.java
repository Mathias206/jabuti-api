package com.mathias.jabuti.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class ApiError {
	private Integer status;
	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
	private List<Field> fields;

	@Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;
	}
}
