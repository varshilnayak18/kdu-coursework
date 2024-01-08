package org.javasession1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    public void logAddStudent(int id) {
        logger.info("Added student with id: {}", id);
    }

    public void logGetStudentById(int id) {
        logger.info("Retrieved student with id: {}", id);
    }

    public void logGetStudentByIdError(int id) {
        logger.warn("Student with id {} not found", id);
    }

    public void logGetStudentByName(String name) {
        logger.info("Retrieved student with name: {}", name);
    }

    public void logGetStudentByNameError(String name) {
        logger.warn("Student with name {} not found", name);
    }

    public void logSetStudent(int id) {
        logger.info("Updated student with id: {}", id);
    }

    public void logSetStudentError(int id) {
        logger.warn("Student with id {} not found", id);
    }
}
