/**
 * 
 */
package com.issac.idp.util;

/**
 * @author issac
 *
 */
public class Util {

	public static final boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	public static final boolean hasContent(String str) {
		return str != null && !str.isEmpty();
	}
}
