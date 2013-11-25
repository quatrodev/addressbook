package quatrocircle.addressbook.service.crud;

import quatrocircle.addressbook.model.AddressBookItem;

public interface AddressBookDAO {
	public void createAddressBookItem(final AddressBookItem addressBookItem)
			throws Exception;

	public void deleteAddressBookItem(final AddressBookItem addressBookItem)
			throws Exception;

	public String getAddressBookItemAsJson(final AddressBookItem addressBookItem)
			throws Exception;

	public void updateAddressBookItem(final AddressBookItem addressBookItem)
			throws Exception;

}
