package com.makemyday.service;

import com.makemyday.entities.Device;

public interface DeviceService
{
	boolean createDevice(Device device);

	Device getDevice(String udid);
}
