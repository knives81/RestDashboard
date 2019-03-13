package com.dashboard.restservicedashboard.error;

public class NoObjectFoundInRestException extends RuntimeException{
	public NoObjectFoundInRestException() {
        super();
    }
    public NoObjectFoundInRestException(String s) {
        super(s);
    }

}
