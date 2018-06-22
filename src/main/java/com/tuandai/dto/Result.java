package com.tuandai.dto;

import com.tuandai.enums.ReturnStatus;

import java.util.HashMap;
import java.util.Map;

public class Result<T> {

	private T data;

	private String returnMessage;

	private int returnStatus;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public int getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(int returnStatus) {
		this.returnStatus = returnStatus;
	}

	/************************** 静态调用方法 ******************************/
	public static Result success() {
		return success(null);
	}

	public static Result success(Object data) {
		return getResult(ReturnStatus.SUCCESS, data);
	}

	public static Result success(Object data, String token, int timeCode, Map timeData) {
		if (data == null) data = new HashMap<>();
		if (timeData == null) data =new HashMap<>();
		return getResult(ReturnStatus.SUCCESS, data);
	}

	public static Result fail(ReturnStatus returnStatus) {
		return fail(returnStatus, null);
	}


	public static Result fail(ReturnStatus returnStatus, Object data) {
		return getResult(returnStatus, data);
	}


	public static Result fail(int returnStatus, String message) {
		Result result = new Result();
		result.setReturnStatus(returnStatus);
		result.setReturnMessage(message);
		return result;
	}

	public static Result fail(int returnStatus, String message, Object data) {
		Result result = new Result();
		result.setReturnStatus(returnStatus);
		result.setReturnMessage(message);
		result.setData(data);
		return result;
	}

	private static Result getResult(ReturnStatus returnStatus, Object data) {
		Result result = new Result();
		if (data == null) data = new HashMap<>();
		result.setData(data);
		result.setReturnStatus(returnStatus.getCode());
		result.setReturnMessage(returnStatus.getMsg());
		return result;
	}
}
