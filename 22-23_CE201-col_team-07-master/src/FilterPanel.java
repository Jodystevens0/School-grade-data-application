import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterPanel extends JPanel implements ItemListener {
    HashMap<JCheckBox, String> map;
    JCheckBox selectAll;

    public FilterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        map = new HashMap<>();

        selectAll = new JCheckBox("Select All");
        selectAll.setSelected(true);
        selectAll.addActionListener(e -> {
            for (JCheckBox cb : map.keySet()) {
                cb.setSelected(selectAll.isSelected());
            }
        });

        add(selectAll);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        add(separator);
    }

    public void fillPanel(ArrayList<String> items) {
        clear();

        if (items == null || items.size() == 0)
            return;

        ArrayList<String> sorted = (ArrayList<String>)items.clone();
        sorted.sort(String::compareTo);
        for (String s : items) {
            JCheckBox cb = new JCheckBox(s);
            cb.setSelected(selectAll.isSelected());
            cb.addItemListener(this);

            add(cb);

            map.put(cb, s);
        }
    }

    public ArrayList<String> getFiltered() {
        ArrayList<String> filtered = new ArrayList<>();
        for (JCheckBox val : map.keySet()) {
            if (val.isSelected()) {
                filtered.add(map.get(val));
            }
        }

        return filtered;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (!((JCheckBox)e.getSource()).isSelected() && selectAll.isSelected()) {
            selectAll.setSelected(false);
        }
    }

    private void clear() {
        for (JCheckBox cb : map.keySet()) {
            remove(cb);
        }
        map = new HashMap<>();
    }
}
