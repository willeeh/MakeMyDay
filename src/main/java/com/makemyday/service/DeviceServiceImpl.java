package com.makemyday.service;

import com.google.inject.Inject;
import com.makemyday.dao.Dao;
import com.makemyday.entities.Device;
import org.bson.types.ObjectId;

public class DeviceServiceImpl implements DeviceService
{

	private final Dao<Device, ObjectId> deviceDAO;

	@Inject
	public DeviceServiceImpl(Dao<Device, ObjectId> deviceDAO)
	{
		this.deviceDAO = deviceDAO;
	}

	@Override
	public void createDevice(Device device)
	{
		Device existingDevice = deviceDAO.findOne("udid", device.getUdid());

		if (existingDevice == null)
		{
			deviceDAO.save(device);
		}

	}

	@Override
	public Device getDevice(String udid)
	{
		return deviceDAO.findOne("udid", udid);
	}
}
