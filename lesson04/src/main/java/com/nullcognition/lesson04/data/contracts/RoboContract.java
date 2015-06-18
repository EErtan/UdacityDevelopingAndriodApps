package com.nullcognition.lesson04.data.contracts;// Created by ersin on 17/06/15

import android.provider.BaseColumns;

/*  BaseColumns - just consists of two strings
	String	_COUNT	The count of rows in a directory.
	String	_ID	The unique ID for a row.
*/

public class RoboContract{

	// date normalizer - for data that can be compared, but is comprised of multiple
	// components, you want to normalize it(aggregate the values into one value)

	public static final class HardwareEntry implements BaseColumns{

		public static final String TABLE_NAME = "hardware";

		// foreign key to the software table
		public static final String COL_SOFTWARE_KEY = "software_id";

		// ids are for numerically based data
		public static final String COL_MODEL_ID = "model_id";
		public static final String COL_FIRMWARE_ID = "firmware_id";

		// columns of components
		public static final String COL_RAM = "ram";
		public static final String COL_CPU = "cpu";
		public static final String COL_LAB = "look_aside_buffer";
		public static final String COL_BUS = "bus";
		public static final String COL_CACHE = "cache";
	}

	public static final class SoftwareEntry implements BaseColumns{

		public static final String TABLE_NAME = "software";

		public static final String COL_VM_ID = "vm_id";

		// descriptions may be required, including unit types
		// Heap:Mb {deep learning systems, large memory, slower access}
		public static final String COL_HEAP = "heap";
		// Stack:Mb {perceptual systems, fast access, cpu managed, local scope}
		public static final String COL_STACK = "stack";
		// Program:.class {AI priorities, capabilities}
		public static final String COL_PROGRAM = "program";
	}
}
