package jouk.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class SwaggerApiRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		this.restConfiguration()
				.component("netty4-http").bindingMode(RestBindingMode.json)//
				.dataFormatProperty("prettyPrint", "true")//
				.contextPath("/").port(8090)//
				.apiContextPath("/api-doc")//
				.apiProperty("api.title", "User API").apiProperty("api.version", "1")//
				.apiProperty("cors", "true");

		this.rest().description("User rest service")//
				.consumes("application/json").produces("application/json")//

				.get("/readiness").to("direct:readiness") //
				.get("/liveness").to("direct:liveness") //

				.get("/users").description("List all users").outType(User[].class) //
				.to("direct:listUsers")

				.get("/users/{id}").description("Find user by id").outType(User.class) //
				.param().name("id").type(RestParamType.path).description("The id of the user to get").dataType("String").endParam() //
				.to("direct:getUser")//

				.put("/users").description("Updates or create a user").type(User.class) //
				.param().name("body").type(RestParamType.body).description("The user to update or create").endParam() //
				.to("direct:updateUser");

		this.from("direct:liveness").transform().constant("isalive");
		this.from("direct:readiness").transform().constant("isready");

		this.from("direct:listUsers").to("bean:userService?method=getUserList");
		this.from("direct:getUser").to("bean:userService?method=getUser(${header.id})");
		this.from("direct:updateUser").to("bean:userService?method=updateUser(${body})");
	}

}
