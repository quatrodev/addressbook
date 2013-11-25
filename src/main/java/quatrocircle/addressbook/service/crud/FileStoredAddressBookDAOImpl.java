package quatrocircle.addressbook.service.crud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import quatrocircle.addressbook.model.AddressBookItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Singleton
@Service
public class FileStoredAddressBookDAOImpl implements AddressBookDAO {

	@Override
	public void createAddressBookItem(final AddressBookItem addressBookItem)
			throws Exception {
		if (StringUtils.isNotEmpty(getAddressBookItemAsJson(addressBookItem)))
			throw new IllegalArgumentException();

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		String value = gson.toJson(addressBookItem);
		String key = addressBookItem.getEmail();
		writeToFile(key, value);
	}

	@Override
	public void deleteAddressBookItem(AddressBookItem addressBookItem)
			throws Exception {
		if (StringUtils.isEmpty(getAddressBookItemAsJson(addressBookItem)))
			throw new IllegalArgumentException();

		File file = new File("addressbook.txt");
		List<String> lines = FileUtils.readLines(file);
		List<String> newLines = FileUtils.readLines(file);

		for (String line : lines) {
			if (line.contains(addressBookItem.getEmail())) {
				newLines.remove(line);

				String value = line.split("value =  ")[1];

				Gson gson = new Gson();
				AddressBookItem item = gson.fromJson(value,
						AddressBookItem.class);
				item.setDeleted(true);

				Gson gsonBuilder = new GsonBuilder()
						.excludeFieldsWithoutExposeAnnotation().create();
				String json = gsonBuilder.toJson(item);

				newLines.add("key = " + addressBookItem.getEmail()
						+ " || value =  " + json + "\n");
			}
		}

		FileUtils.deleteQuietly(file);
		for (String line : newLines) {
			FileUtils.writeStringToFile(file, line, true);
		}
	}

	@Override
	public String getAddressBookItemAsJson(AddressBookItem addressBookItem)
			throws Exception {
		return readFile(addressBookItem.getEmail());
	}

	@Override
	public void updateAddressBookItem(AddressBookItem addressBookItem)
			throws Exception {
		if (StringUtils.isEmpty(getAddressBookItemAsJson(addressBookItem)))
			throw new IllegalArgumentException();

		File file = new File("addressbook.txt");
		List<String> lines = FileUtils.readLines(file);
		List<String> newLines = FileUtils.readLines(file);

		for (String line : lines) {
			if (line.contains(addressBookItem.getEmail())) {
				newLines.remove(line);

				Gson gson = new GsonBuilder()
						.excludeFieldsWithoutExposeAnnotation().create();
				String value = gson.toJson(addressBookItem);

				newLines.add("key = " + addressBookItem.getEmail()
						+ " || value =  " + value + "\n");
			}
		}

		FileUtils.deleteQuietly(file);
		for (String line : newLines) {
			FileUtils.writeStringToFile(file, line, true);
		}
	}

	private void writeToFile(final String key, final String value)
			throws IOException {
		File file = new File("addressbook.txt");
		FileUtils.writeStringToFile(file, "key = " + key + " || value =  "
				+ value + "\n", true);
	}

	private String readFile(final String key) throws IOException {
		File file = new File("addressbook.txt");

		try {
			List<String> lines = FileUtils.readLines(file);

			for (String line : lines) {
				if (line.contains(key)) {
					String value = line.split("value =  ")[1];

					Gson gson = new Gson();
					AddressBookItem addressBookItem = gson.fromJson(value,
							AddressBookItem.class);

					if (!addressBookItem.isDeleted())
						return value;
				}
			}
		} catch (FileNotFoundException e) {

		}

		return StringUtils.EMPTY;
	}

}