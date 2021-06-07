package br.com.phbit.cryptography.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties read(String propertiesName) throws IOException, FileNotFoundException {
		Properties properties = new Properties();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(propertiesName);

		if (inputStream == null)
			throw new FileNotFoundException("Configuration file '" + propertiesName + "' not found in project!");

		properties.load(inputStream);
		return properties;
	}
}
