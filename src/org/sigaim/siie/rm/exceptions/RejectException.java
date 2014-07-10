package org.sigaim.siie.rm.exceptions;

public class RejectException extends Exception {
	private String requestId;
	private CSReason reason;
	
	private static final long serialVersionUID = 1L;
	
	public RejectException(String requestId, CSReason reason) {
		this.requestId=requestId;
		this.reason=reason;
	}
	public String getRequestId() {
		return requestId;
	}

	public CSReason getReason() {
		return reason;
	}
}
