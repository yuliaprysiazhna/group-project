package pwo.group.project;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTrainingTest {

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
        var addTraining = new AddTraining();
        var buttonsCount = getAllComponents(addTraining).stream()
            .filter(c -> c.getClass() == JButton.class)
            .count();
        var labelsCount = getAllComponents(addTraining).stream()
            .filter(c -> c.getClass() == JLabel.class)
            .count();
        var textFieldsCount = getAllComponents(addTraining).stream()
            .filter(c -> c.getClass() == JTextField.class)
            .count();

        assertEquals(2, buttonsCount);
        assertEquals(3, labelsCount);
        assertEquals(3, textFieldsCount);
    }
}
