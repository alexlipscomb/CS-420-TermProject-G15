default: run

.PHONY: clean run

clean:
	mvn clean

run:
	mvn clean javafx:run

