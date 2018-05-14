package com.orm.utils;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Sets;
import com.iss.platform.user.entity.User;

public class Singleton {

	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<Set<String>> premissionLocal = new ThreadLocal<Set<String>>();

	private final static String USER_INFO = "USER_INFO";

	public static void initLocal(HttpServletRequest request, HttpServletResponse response) {
		requestLocal.set(request);
		responseLocal.set(response);
	}

	public final static HttpServletRequest get() {
		return requestLocal.get();
	}

	public final static HttpServletResponse getResponse() {
		return responseLocal.get();
	}

	public static void setUserInfo(Object userInfo, Set<String> premissions) {
		get().getSession().setAttribute(USER_INFO, userInfo);
		premissionLocal.set(premissions);
	}

	public final static User getUserInfo() {
		Object object = get().getSession().getAttribute(USER_INFO);
		if (object != null)
			return (User) object;
		return null;
	}

	public final static Set<String> getPremissions() {
		if (premissionLocal.get() != null ) {
			return premissionLocal.get();
		}
		return Sets.newConcurrentHashSet();
	}
}
