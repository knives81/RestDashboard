package com.dashboard.restservicedashboard.error;

public class NoObjectFoundInDbException extends RuntimeException{
	public NoObjectFoundInDbException() {
        super();
    }
    public NoObjectFoundInDbException(String s) {
        super(s);
    }

}
