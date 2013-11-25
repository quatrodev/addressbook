package quatrocircle.addressbook.controller;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import quatrocircle.addressbook.model.AddressBookItem;
import quatrocircle.addressbook.service.crud.FileStoredAddressBookDAOImpl;
import quatrocircle.addressbook.service.validation.AddressBookItemValidator;
import quatrocircle.addressbook.spring.ApplicationContextAccessor;

@Path("/addressbook")
public class AddressBookRestfullController {

	@Autowired
	AddressBookItemValidator addressBookItemValidator;

	@Autowired
	FileStoredAddressBookDAOImpl fileAddressBookDAOImpl;

	@Autowired
	ApplicationContextAccessor applicationContext;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/plain")
	public Response create(@BeanParam AddressBookItem addressBookItem) {

		List<String> messages = addressBookItemValidator
				.validate(addressBookItem);

		if (messages.isEmpty()) {
			try {
				fileAddressBookDAOImpl.createAddressBookItem(addressBookItem);
			} catch (IllegalArgumentException e) {
				return Response.status(Response.Status.CONFLICT)
						.entity(" Cant Create Duplicate Email  ").build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(" File write Error " + e.getMessage()).build();
			}

			return Response.status(Response.Status.CREATED).entity("OK")
					.build();
		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(StringUtils.join(messages.toArray(), ',')).build();
	}

	@GET
	@Path("{email}")
	@Produces("application/json")
	public Response read(@PathParam("email") String email) {

		List<String> messages = addressBookItemValidator.validateEmail(email);

		if (messages.isEmpty()) {
			try {
				AddressBookItem addressBookItem = applicationContext
						.getBean(AddressBookItem.class);
				addressBookItem.setEmail(email);

				String itemAsJson = fileAddressBookDAOImpl
						.getAddressBookItemAsJson(addressBookItem);

				if (StringUtils.isNotEmpty(itemAsJson))
					return Response.status(Response.Status.OK)
							.entity(itemAsJson).build();

				return Response.status(Response.Status.NOT_FOUND)
						.entity("addressbook item not found").build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(" File write Error " + e.getMessage()).build();
			}

		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(StringUtils.join(messages.toArray(), ',')).build();
	}

	@DELETE
	@Path("{email}")
	@Produces("text/plain")
	public Response delete(@PathParam("email") String email) {

		List<String> messages = addressBookItemValidator.validateEmail(email);

		if (messages.isEmpty()) {
			try {
				AddressBookItem addressBookItem = applicationContext
						.getBean(AddressBookItem.class);
				addressBookItem.setEmail(email);

				fileAddressBookDAOImpl.deleteAddressBookItem(addressBookItem);

				return Response.status(Response.Status.OK).entity("deleted")
						.build();
			} catch (IllegalArgumentException e) {
				return Response.status(Response.Status.NOT_FOUND)
						.entity("addressbook item not found").build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(" File write Error " + e.getMessage()).build();
			}

		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(StringUtils.join(messages.toArray(), ',')).build();
	}

	@PUT
	@Path("{email}")
	@Produces("text/plain")
	public Response update(@BeanParam AddressBookItem addressBookItem,
			@PathParam("email") String email) {

		addressBookItem.setEmail(email);
		List<String> messages = addressBookItemValidator
				.validate(addressBookItem);

		if (messages.isEmpty()) {
			try {
				fileAddressBookDAOImpl.updateAddressBookItem(addressBookItem);
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(" File write Error " + e.getMessage()).build();
			}

			return Response.status(Response.Status.CREATED).entity("OK")
					.build();
		}

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(StringUtils.join(messages.toArray(), ',')).build();
	}

}
