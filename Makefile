default: run

.PHONY: clean run test

clean:
	mvn clean

run:
	mvn clean javafx:run

test:
	mvn test
