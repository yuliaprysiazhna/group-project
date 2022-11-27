package pwo.group.project;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingListTest {

    public static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container container)
                compList.addAll(getAllComponents(container));
        }
        return compList;
    }

    @Test
    public void testControlsCount() {
        var trainingList = new TrainingList();
        var buttonsCount = getAllComponents(trainingList).stream()
            .filter(c -> c.getClass() == JButton.class)
            .count();
        var tablesCount = getAllComponents(trainingList).stream()
            .filter(c -> c.getClass() == JTable.class)
            .count();

        assertEquals(1, buttonsCount);
        assertEquals(1, tablesCount);
    }
}
