package quatrocircle.addressbook.rabbitmq;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quatrocircle.addressbook.model.AddressBookItem;
import quatrocircle.addressbook.service.crud.FileStoredAddressBookDAOImpl;
import quatrocircle.addressbook.service.validation.AddressBookItemValidator;

import com.google.gson.Gson;

@Service
@Singleton
public class AddressBookListener {

	@Autowired
	AddressBookItemValidator addressBookItemValidator;

	@Autowired
	FileStoredAddressBookDAOImpl fileAddressBookDAOImpl;

	public void create(String json) {
		try {
			fileAddressBookDAOImpl
					.createAddressBookItem(createAddressBoookItemFromJson(json));
		} catch (Exception e) {
		}
	}

	public void update(String json) {

		try {
			fileAddressBookDAOImpl
					.updateAddressBookItem(createAddressBoookItemFromJson(json));
		} catch (Exception e) {
		}
	}

	public void delete(String json) {

		try {
			fileAddressBookDAOImpl
					.deleteAddressBookItem(createAddressBoookItemFromJson(json));
		} catch (Exception e) {
		}
	}

	public String read(String json) {
		try {
			return fileAddressBookDAOImpl
					.getAddressBookItemAsJson(createAddressBoookItemFromJson(json));
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}

	}

	private AddressBookItem createAddressBoookItemFromJson(String json) {
		Gson gson = new Gson();
		AddressBookItem addressBookItem = gson.fromJson(json,
				AddressBookItem.class);
		return addressBookItem;
	}
}
