package jouk.hawt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HawtRunner {
	private static final Logger LOG = LoggerFactory.getLogger(HawtRunner.class);

	public static void run() {
		System.setProperty("hawtio.authenticationEnabled", "false");
		io.hawt.embedded.Main hawt = new io.hawt.embedded.Main();
		hawt.setWarLocation("lib/hawt");

		try {
			hawt.run();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private HawtRunner() {
		super();
	}

}
