package jouk.rest;

import org.apache.camel.main.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jouk.hawt.HawtRunner;

public class SwaggerRestApp {
	private static final Logger LOG = LoggerFactory.getLogger(SwaggerRestApp.class);

	public static void main(String[] args) {
		HawtRunner.run();

		Main camel = new Main();

		camel.addRouteBuilder(new SwaggerApiRestRoute());
		try {
			camel.run();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
