package com.nullcognition.lesson04.data.contracts;// Created by ersin on 18/06/15

import android.provider.BaseColumns;

import java.sql.Time;

public class CloudContract{

	public static class Compute implements BaseColumns{
		public static final String TABLE_NAME = "compute";
		// _id for a row
		// _count of rows in a directory
		// this is the foreign key
		public static final String COL_OWNER_ID = "OWNER_id";
		public static final String COL_PROJECT_ID = "project_id";

		// project service name
		public static final String COL_CLOUD_ENDPOINTS = "cloud_endpoints";
		public static final String COL_APP_ENGINE = "app_engine";
		public static final String COL_MANAGED_VMS = "managed_vms";
		public static final String COL_CONTAINER_ENGINE = "container_engine";
		public static final String COL_COMPUTE_ENGINE = "compute_engine";
	}

	public static class OWNER implements BaseColumns{
		public static final String TABLE_NAME = "owner";
		// _id for row
		// _count of rows in a dir
		public static final String COL_BILLING_ID = "billing_id";
		public static final String COL_COMPUTE_ID = "compute_id";
		public static final String COL_PROJECTS_ID = "projects_id";

		// these should be part of a credentials id table
		public static final String COL_EMAIL = "email";
		public static final String COL_ADRESS = "address";
		// ...
	}

	public static long normalizeDate(long startDate) {
		// normalize the start date to the beginning of the (UTC) day
		android.text.format.Time time = new android.text.format.Time();
		time.set(startDate);
		int julianDay = android.text.format.Time.getJulianDay(startDate, time.gmtoff);
		return time.setJulianDay(julianDay);
	}

}
