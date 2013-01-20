package com.makemyday.service;

import com.makemyday.entities.Device;

public interface DeviceService
{
	/**
	 * Create a new device if it doesn't exists
	 * @param device
	 */
	void createDevice(Device device);

	/**
	 * Gets a device by its udid
	 * @param udid
	 * @return
	 */
	Device getDevice(String udid);
}
