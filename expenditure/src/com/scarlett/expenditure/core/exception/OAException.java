package com.scarlett.expenditure.core.exception;

/**
 * 基础异常类
 *@intention 
 * <p> 用于处理业务异常 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class OAException extends RuntimeException {

	private static final long serialVersionUID = -7223759887890563884L;

	public OAException() {
		super();
	}

	public OAException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OAException(String message, Throwable cause) {
		super(message, cause);
	}

	public OAException(String message) {
		super(message);
	}

	public OAException(Throwable cause) {
		super(cause);
	}
}
