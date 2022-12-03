package com.issac.idp.exception;

/**
 * 
 * @author issac
 *
 */
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public RecordNotFoundException(String str){
		super(str);
	}
}
