package quatrocircle.addressbook.model;

import javax.ws.rs.FormParam;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
@Scope(value = "prototype")
public class AddressBookItem {

	@FormParam("email")
	private String email;

	@FormParam("socialSecurityId")
	@Expose
	private String socialSecurityId;

	@FormParam("firstName")
	@Expose
	private String firstName;

	@FormParam("lastName")
	@Expose
	private String lastName;

	@FormParam("streetAddress")
	@Expose
	private String streetAddress;

	@FormParam("city")
	@Expose
	private String city;

	@FormParam("state")
	@Expose
	private String state;

	@FormParam("zipCode")
	@Expose
	private String zipCode;

	@FormParam("phoneNumber")
	@Expose
	private String phoneNumber;

	@FormParam("lifeStory")
	@Expose
	private String lifeStory;

	@FormParam("locationOfImage")
	@Expose
	private String locationOfImage;

	@Expose
	private boolean deleted;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSocialSecurityId() {
		return socialSecurityId;
	}

	public void setSocialSecurityId(String socialSecurityId) {
		this.socialSecurityId = socialSecurityId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLifeStory() {
		return lifeStory;
	}

	public void setLifeStory(String lifeStory) {
		this.lifeStory = lifeStory;
	}

	public String getLocationOfImage() {
		return locationOfImage;
	}

	public void setLocationOfImage(String locationOfImage) {
		this.locationOfImage = locationOfImage;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}