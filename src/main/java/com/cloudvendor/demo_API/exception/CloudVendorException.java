package com.cloudvendor.demo_API.exception;

import org.springframework.http.HttpStatus;

public record CloudVendorException(String message, Throwable cause, HttpStatus httpStatus) {
}
