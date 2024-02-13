import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;


public class Gui extends JFrame {
    private List<Student> students;

    private JFreeChart scatterPlot;
    private TabbedFilterPane filterPane;

    public Gui(String title) {
        setTitle(title);
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        scatterPlot = ChartFactory.createScatterPlot("Student data", "Module", "Score", null);
        ChartPanel chartPanel = new ChartPanel(scatterPlot);
        chartPanel.setPreferredSize(new Dimension(600, 600));

        filterPane = new TabbedFilterPane() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                super.itemStateChanged(e);
                updateGraph();
            }
        };
        filterPane.setPreferredSize(new Dimension(320, 600));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openItem.addActionListener((e -> {
            FileDialog fd = new FileDialog(this, "Select a Student data file:", FileDialog.LOAD);
            fd.setFile("*.csv");
            fd.setVisible(true);
            String filename = fd.getFile();

            students = Student.loadStudents(filename);

            if (students == null) {
                JOptionPane.showMessageDialog(this, "Invalid student data file.", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                ArrayList<String> studentNames = new ArrayList<>();
                ArrayList<String> courseNames = new ArrayList<>();
                for (Student s : students) {
                    studentNames.add(""+s.getRegNo());
                    if (!courseNames.contains(s.getCourse()))
                        courseNames.add(s.getCourse());
                }
                filterPane.studentsPanel.fillPanel(studentNames);
                filterPane.coursesPanel.fillPanel(courseNames);

                ArrayList<String> moduleNames = new ArrayList<>();
                for (Modules m : Modules.values()) {
                    moduleNames.add(m.getName());
                }
                filterPane.modulesPanel.fillPanel(moduleNames);

                updateGraph();
            }
        }));

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        fileMenu.add(openItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(chartPanel, BorderLayout.CENTER);
        contentPane.add(filterPane, BorderLayout.WEST);
    }

    public void updateGraph() {
        List<String> modules = filterPane.modulesPanel.getFiltered();
        List<String> courses = filterPane.coursesPanel.getFiltered();

        modules.sort(String::compareTo);
        Object[] arr = modules.toArray();
        SymbolAxis axis = new SymbolAxis("Modules", Arrays.copyOf(arr, arr.length, String[].class));
        scatterPlot.getXYPlot().setDomainAxis(axis);

        List<String> filteredStudents = filterPane.studentsPanel.getFiltered();

        XYSeriesCollection data = new XYSeriesCollection();
        for (Student s : students) {
            if (!filteredStudents.contains(""+s.getRegNo()))
                continue;

            if (!courses.contains(s.getCourse()))
                continue;

            XYSeries series = new XYSeries(""+s.getRegNo());

            for (Grade g : s.getGrades()) {
                int i = modules.indexOf(g.getModule().getName());
                if (i != -1)
                    series.add(i, g.getGrade());
            }
            if (series.getItemCount() > 0)
                data.addSeries(series);
        }
        scatterPlot.getXYPlot().setDataset(data);
    }

    public static void main(String[] args) {
        Gui gui = new Gui("CE201 Team 7");
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}

