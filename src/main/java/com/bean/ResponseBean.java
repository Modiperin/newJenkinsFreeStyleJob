package com.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> {
	
	T data;
	String message;
//	public T getData() {
//		return data;
//	}
//	public void setData(T data) {
//		this.data = data;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
}
