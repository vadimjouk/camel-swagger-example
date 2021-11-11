package jouk.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class SwaggerApiRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		this.restConfiguration().component("netty4-http").bindingMode(RestBindingMode.json)//
				// this.restConfiguration().component("restlet").host("localhost").bindingMode(RestBindingMode.json)//
				.dataFormatProperty("prettyPrint", "true")//
				.contextPath("/").port(8090)//
				.apiContextPath("/api-doc")//
				.apiProperty("api.title", "User API").apiProperty("api.version", "1")//
				.apiProperty("cors", "true");

		this.rest("/user").description("User rest service")//
				.consumes("application/json").produces("application/json")//

				.get("/hello").to("direct:hello") //

				.get("/{id}").description("Find user by id").outType(User.class) //
				.param().name("id").type(RestParamType.path).description("The id of the user to get").dataType("int").endParam() //
				// .to("mock:getUser")//
				// .to("bean:userService?method=getUser(${header.id})")//
				.to("direct:getUser")//

				.put().description("Updates or create a user").type(User.class) //
				.param().name("body").type(RestParamType.body).description("The user to update or create").endParam() //
				// .to("bean:userService?method=updateUser")//
				.to("mock:updateUser")//

				.get("/findAll").description("Find all users").outType(User[].class) //
				// .to("bean:userService?method=listUsers");
				// .to("mock:listUsers");
				.to("direct:listUsers");

		this.from("direct:hello").transform().constant(new User());
		this.from("direct:getUser").transform().constant(new User());

		List<User> allUsers = new ArrayList<>();
		allUsers.add(new User());
		allUsers.add(new User());

		this.from("direct:listUsers").transform().constant(allUsers);
	}

}
