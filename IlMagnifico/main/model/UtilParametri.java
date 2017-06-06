package main.model;

import java.io.Serializable;

public class UtilParametri implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8064125052761164759L;
	Object[] parametri;

	public UtilParametri() {
		this.parametri = new Object[13];
	}

	public Object[] getParametri() {
		return this.parametri;
	}

	public void setParametri(Object[] parametri) {
		this.parametri = parametri;
	}
}
