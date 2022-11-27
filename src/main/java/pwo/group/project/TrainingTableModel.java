package pwo.group.project;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

import pwo.group.project.training.TrainingsService;
import pwo.group.project.training.Training;
import pwo.group.project.training.DefaultTrainingsService;

public class TrainingTableModel extends AbstractTableModel {

    protected static final String[] COLUMN_NAMES = {
        "Name",
        "When",
        "Length (mins)"
    };

    private String filePath = "trainings.txt";

    private TrainingsService trainingsService;

    private ArrayList<Training> rowData;

    public TrainingTableModel() {
        trainingsService = new DefaultTrainingsService();
        File f = new File(filePath);
        if (f.exists() && !f.isDirectory()) {
            rowData = trainingsService.readAllTrainings(filePath);
        } else {
            rowData = new ArrayList<>();
        }
    }

    public void add(Training... pd) {
        add(new ArrayList<>(Arrays.asList(pd)));
    }

    public void add(ArrayList<Training> pd) {
        rowData.addAll(pd);
        fireTableDataChanged();
        trainingsService.writeAllTrainings(rowData, filePath);
    }

    @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public Training getTrainingDataAt(int row) {
        return rowData.get(row);
    }

    @Override
    public Class getColumnClass(int column) {
        return switch (column) {
            case 0 -> String.class;
            case 1 -> LocalDateTime.class;
            case 2 -> Integer.class;
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int i, int j) {
        var t = getTrainingDataAt(i);

        return switch (j) {
            case 0 -> t.getName();
            case 1 -> t.getDateTime();
            case 2 -> t.getMinutesLength();
            default -> null;
        };
    }

}
