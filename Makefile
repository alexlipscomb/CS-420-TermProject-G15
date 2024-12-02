default: run

.PHONY: clean install run test

clean:
	mvn clean

install:
	mvn install

run:
	mvn clean javafx:run

test:
	mvn test

