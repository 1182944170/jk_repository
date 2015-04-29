package com.rpframework.core.utils.cache;

public class KVString {
	
	public String k;
	public String v;
	
	public KVString(){}
	public KVString(String k, String v){
		this.k = k;
		this.v = v;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
}