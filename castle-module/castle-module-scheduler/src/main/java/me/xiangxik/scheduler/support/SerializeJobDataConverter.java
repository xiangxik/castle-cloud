package me.xiangxik.scheduler.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.quartz.JobDataMap;

@Converter
public class SerializeJobDataConverter implements AttributeConverter<JobDataMap, byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(JobDataMap attribute) {
		if (attribute == null) {
			return null;
		}

		ByteArrayOutputStream baos;
		try {
			baos = serializeJobData(attribute);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return (baos == null) ? new byte[0] : baos.toByteArray();
	}

	@Override
	public JobDataMap convertToEntityAttribute(byte[] dbData) {
		if (dbData == null) {
			return null;
		}

		Map<?, ?> map = null;

		try {
			if (canUseProperties()) {
				InputStream binaryInput = new ByteArrayInputStream(dbData);
				Properties properties = new Properties();
				if (binaryInput != null) {
					try {
						properties.load(binaryInput);
					} finally {
						binaryInput.close();
					}
				}
				map = convertFromProperty(properties);

			} else {
				Object obj = null;

				InputStream binaryInput = new ByteArrayInputStream(dbData);
				if (binaryInput instanceof ByteArrayInputStream
						&& ((ByteArrayInputStream) binaryInput).available() == 0) {
					// do nothing
				} else {
					ObjectInputStream in = new ObjectInputStream(binaryInput);
					try {
						obj = in.readObject();
					} finally {
						in.close();
					}
				}

				map = (Map<?, ?>) obj;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (null != map) {
			return new JobDataMap(map);
		}
		return null;
	}

	protected Map<?, ?> convertFromProperty(Properties properties) throws IOException {
		return new HashMap<Object, Object>(properties);
	}

	protected Object getObjectFromBlob(ResultSet rs, String colName)
			throws ClassNotFoundException, IOException, SQLException {
		Object obj = null;

		Blob blobLocator = rs.getBlob(colName);
		if (blobLocator != null && blobLocator.length() != 0) {
			InputStream binaryInput = blobLocator.getBinaryStream();

			if (null != binaryInput) {
				if (binaryInput instanceof ByteArrayInputStream
						&& ((ByteArrayInputStream) binaryInput).available() == 0) {
					// do nothing
				} else {
					ObjectInputStream in = new ObjectInputStream(binaryInput);
					try {
						obj = in.readObject();
					} finally {
						in.close();
					}
				}
			}

		}
		return obj;
	}

	protected boolean canUseProperties() {
		return false;
	}

	protected ByteArrayOutputStream serializeJobData(JobDataMap data) throws IOException {
		if (canUseProperties()) {
			return serializeProperties(data);
		}

		try {
			return serializeObject(data);
		} catch (NotSerializableException e) {
			throw new NotSerializableException(e.getMessage());
		}
	}

	protected ByteArrayOutputStream serializeObject(Object obj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (null != obj) {
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(obj);
			out.flush();
		}
		return baos;
	}

	private ByteArrayOutputStream serializeProperties(JobDataMap data) throws IOException {
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		if (null != data) {
			Properties properties = convertToProperty(data.getWrappedMap());
			properties.store(ba, "");
		}

		return ba;
	}

	protected Properties convertToProperty(Map<?, ?> data) throws IOException {
		Properties properties = new Properties();

		for (Iterator<?> entryIter = data.entrySet().iterator(); entryIter.hasNext();) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) entryIter.next();

			Object key = entry.getKey();
			Object val = (entry.getValue() == null) ? "" : entry.getValue();

			if (!(key instanceof String)) {
				throw new IOException("JobDataMap keys/values must be Strings "
						+ "when the 'useProperties' property is set. " + " offending Key: " + key);
			}

			if (!(val instanceof String)) {
				throw new IOException("JobDataMap values must be Strings "
						+ "when the 'useProperties' property is set. " + " Key of offending value: " + key);
			}

			properties.put(key, val);
		}

		return properties;
	}
}
