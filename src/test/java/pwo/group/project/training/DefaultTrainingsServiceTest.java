package pwo.group.project.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultTrainingsServiceTest {

    private static final LocalDateTime TRAINING_ONE_DATE = LocalDateTime.parse("20.09.2022 12:21", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

    private static final LocalDateTime TRAINING_TWO_DATE = LocalDateTime.parse("14.03.2021 11:31", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

    private TrainingsService trainingsService;

    private List<Training> expectedOutput;

    @BeforeEach
    void setUp() {
        trainingsService = new DefaultTrainingsService();
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        expectedOutput = List.of(new Training("Training 1", TRAINING_ONE_DATE, 90),
            new Training("Training 2", TRAINING_TWO_DATE, 60));
    }

    @Test
    public void testWriteReadLikeOriginal() throws IOException {
        var file = File.createTempFile("temp", ".file");
        var filePath = file.getAbsolutePath();

        trainingsService.writeAllTrainings(expectedOutput, filePath);
        var result = trainingsService.readAllTrainings(filePath);

        assertEquals(expectedOutput.size(), result.size());
        assertTrue(result.containsAll(expectedOutput));

        file.delete();
    }
}
