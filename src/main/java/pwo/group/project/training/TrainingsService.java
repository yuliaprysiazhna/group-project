package pwo.group.project.training;

import java.util.ArrayList;
import java.util.List;

public interface TrainingsService {

    void writeAllTrainings(List<Training> trainings, String filePath);

    ArrayList<Training> readAllTrainings(String filePath);

}
