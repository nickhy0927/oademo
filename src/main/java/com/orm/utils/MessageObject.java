package com.orm.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MessageObject {

	private int responseCode;
	private String responseMessage;
	private Object object;

	private MessageObject() {
	}

	public static MessageObject getDefaultMessageObjectInstance() {
		return new MessageObject();
	}

	public String getResponseMessage() {
		return responseMessage;
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public static class ResultCode {
		public static int SUCCESS = 200;
		public static int UNAUTH = 201;
		public static int FAILIAR = 403;

	}

	public void setErrorMessage(String responseMessage) {
		this.responseMessage = responseMessage;
		this.responseCode = ResultCode.FAILIAR;
	}

	public void setSuccessMessage(String responseMessage) {
		this.responseMessage = responseMessage;
		this.responseCode = ResultCode.SUCCESS;
	}

	public void returnData(HttpServletResponse response, MessageObject messageObject) throws IOException {
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String json = new Gson().toJson(messageObject);
		if (writer != null) {
			writer.write(json);
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
