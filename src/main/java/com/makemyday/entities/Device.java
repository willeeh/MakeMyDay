package com.makemyday.entities;

import com.google.code.morphia.annotations.Entity;
import com.makemyday.entities.base.Identity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(value = "devices", noClassnameStored = true)
public class Device extends Identity
{
	@NotEmpty
	private String udid;

	private String token;

	public String getUdid()
	{
		return udid;
	}

	public void setUdid(String udid)
	{
		this.udid = udid;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
