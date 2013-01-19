package com.makemyday.service;

import com.makemyday.entities.Device;

public interface DeviceService
{
	void createDevice(Device device);

	Device getDevice(String udid);
}
