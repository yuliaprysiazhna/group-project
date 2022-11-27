package pwo.group.project.training;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DefaultTrainingsService implements TrainingsService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public void writeAllTrainings(final List<Training> trainings, final String filePath) {
        final String json = objectMapper.writeValueAsString(trainings);
        final Path path = Paths.get(filePath);

        Files.writeString(path, json);
    }

    @Override
    @SneakyThrows
    public ArrayList<Training> readAllTrainings(final String filePath) {
        final Path path = Paths.get(filePath);
        final String json = Files.readString(path);

        return objectMapper.readValue(json, new TypeReference<ArrayList<Training>>() {

        });
    }
}
