package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;

public class Customer implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4394778516546722633L;
	
	public static final int DEFAULT_CUSTOMER = 0;
	public static final int SILVER_CUSTOMER = 1;
	public static final int GOLD_CUSTOMER = 2;
	
	private int status;
	
	private String name;
	
	public Customer(int status, String name) {
		super();
		this.status = status;
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [status=").append(status).append(", ");
		if (name != null)
			builder.append("name=").append(name);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}	
}
