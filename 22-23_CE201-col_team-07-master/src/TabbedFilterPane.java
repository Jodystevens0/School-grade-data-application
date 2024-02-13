import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TabbedFilterPane extends JTabbedPane implements ItemListener {
    FilterPanel modulesPanel;
    FilterPanel performancePanel;
    FilterPanel studentsPanel;
    FilterPanel coursesPanel;

    public TabbedFilterPane() {
        TabbedFilterPane base = this;
        modulesPanel = new FilterPanel() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                super.itemStateChanged(e);
                base.itemStateChanged(e);
            }
        };

        performancePanel = new FilterPanel() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                super.itemStateChanged(e);
                base.itemStateChanged(e);
            }
        };

        studentsPanel = new FilterPanel() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                super.itemStateChanged(e);
                base.itemStateChanged(e);
            }
        };

        coursesPanel = new FilterPanel() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                super.itemStateChanged(e);
                base.itemStateChanged(e);
            }
        };

        JScrollPane studentsScroll = new JScrollPane(studentsPanel);
        studentsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane modulesScroll = new JScrollPane(modulesPanel);
        modulesScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        modulesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane coursesScroll = new JScrollPane(coursesPanel);
        coursesScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        coursesScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane performanceScroll = new JScrollPane(performancePanel);
        performanceScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        performanceScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        performancePanel.add(new JLabel("Currently unsupported."));

        addTab("Students", studentsScroll);
        addTab("Modules", modulesScroll);
        addTab("Courses", coursesScroll);
        addTab("Performance", performanceScroll);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }
}
