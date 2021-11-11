To create camel based project:

mvn archetype:generate -DarchetypeGroupId=org.apache.camel.archetypes -DarchetypeArtifactId=camel-archetype-java



To run with maven:

mvn compile exec:java -Dexec.mainClass=jouk.file.MainApp