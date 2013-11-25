package quatrocircle.addressbook.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import quatrocircle.addressbook.model.AddressBookItem;

@Service
@Singleton
public class AddressBookItemValidator {

	public List<String> validate(final AddressBookItem addressBookItem) {
		List<String> messages = new ArrayList<>();
		validateFields(addressBookItem, messages);
		return messages;
	}

	public List<String> validateEmail(final String email) {
		List<String> messages = new ArrayList<>();
		checkEmail(email, messages);
		return messages;
	}

	private void checkEmail(final String email, List<String> messages) {
		if (!isValidEmail(email))
			messages.add("Check email");
	}

	private void validateFields(final AddressBookItem addressBookItem,
			List<String> messages) {
		checkEmail(addressBookItem.getEmail(), messages);

		if (!isValidSocialSecurityId(addressBookItem.getSocialSecurityId()))
			messages.add("Check Social security id");

		if (!isValidFirstName(addressBookItem.getFirstName()))
			messages.add("Check first name");

		if (!isValidLastName(addressBookItem.getLastName()))
			messages.add("Check last name");

		if (!isValidStreetAddress(addressBookItem.getStreetAddress()))
			messages.add("Check street address");

		if (!isValidCity(addressBookItem.getCity()))
			messages.add("Check city");

		if (!isValidState(addressBookItem.getState()))
			messages.add("Check state");

		if (!isValidZipCode(addressBookItem.getZipCode()))
			messages.add("Check zipcode");

		if (!isValidPhoneNumber(addressBookItem.getPhoneNumber()))
			messages.add("Check phone number");

		if (!isValidLifeStory(addressBookItem.getLifeStory()))
			messages.add("Check life story");

		if (!isValidLocationOfImage(addressBookItem.getLocationOfImage()))
			messages.add("Check location of image");
	}

	private static boolean isValidEmail(String email) {
		if (email == null || !EmailValidator.getInstance().isValid(email))
			return false;

		return true;
	}

	private static boolean isValidLocationOfImage(String locationOfImage) {
		if (locationOfImage != null
				&& !UrlValidator.getInstance().isValid(locationOfImage))
			return false;

		return true;
	}

	private static boolean isValidLifeStory(String lifeStory) {
		if (lifeStory != null && lifeStory.length() > 250)
			return false;

		return true;
	}

	private static boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber != null
				&& !phoneNumber.matches("^(\\d{3}-?\\d{3}-?\\d{4})$"))
			return false;

		return true;
	}

	private static boolean isValidZipCode(String zipCode) {
		if (zipCode != null && !zipCode.matches("^(\\d{5})$"))
			return false;

		return true;
	}

	private static boolean isValidState(String state) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean isValidCity(String city) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean isValidStreetAddress(String streetAddress) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean isValidLastName(String lastName) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean isValidFirstName(String firstName) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean isValidSocialSecurityId(String socialSecurityId) {
		if (socialSecurityId != null
				&& !socialSecurityId.matches("^(\\d{3}-?\\d{2}-?\\d{4})$"))
			return false;

		return true;
	}

}