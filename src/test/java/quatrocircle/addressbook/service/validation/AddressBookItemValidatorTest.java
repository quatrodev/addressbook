package quatrocircle.addressbook.service.validation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookItemValidatorTest {

	AddressBookItemValidator addressBookItemValidator;

	@Before
	public void setUp() {
		addressBookItemValidator = new AddressBookItemValidator();
	}

	@Test
	public void testInvalidEmail() {
		List<String> messages = addressBookItemValidator.validateEmail("aaa");
		assertThat("invalid email", messages.size(), equalTo(1));
		assertEquals("Check email", messages.get(0));
	}

	@Test
	public void testValidEmail() {
		List<String> messages = addressBookItemValidator
				.validateEmail("aaa@ddd.com");
		assertThat("valid email", messages.size(), equalTo(0));
	}
}
