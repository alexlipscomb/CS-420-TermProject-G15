default: run

.PHONY: clean install package run test

clean:
	mvn clean

install:
	mvn install

package:
	mvn package assembly:single

run:
	mvn clean javafx:run

test:
	mvn test

