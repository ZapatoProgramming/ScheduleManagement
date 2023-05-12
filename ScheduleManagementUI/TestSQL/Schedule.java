import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Schedule extends JFrame{
    private JButton applyButton;
    private JButton addTimeButton;
    private JButton modifyCourseProgrammingButton;
    private JButton deleteCourseButton;
    private JButton timeSchedulesButton;
    private JPanel Sched;


    public Schedule() {
        setContentPane(Sched);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);


    addTimeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                AddTime addTime = new AddTime();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            setVisible(false);
        }
    });
    modifyCourseProgrammingButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                ModCourseProg modCourseProg = new ModCourseProg();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            setVisible(false);
        }
    });
    /*
    deleteCourseButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                DelCourse delCourse = new DelCourse();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            setVisible(false);
        }
    });*/
        /*
    timeSchedulesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                Times times = new Times();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            setVisible(false);
        }
    });*/
}
}
