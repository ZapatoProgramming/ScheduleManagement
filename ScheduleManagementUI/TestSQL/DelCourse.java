import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DelCourse extends JFrame {
    private JTextField StartTime3;
    private JTextField RoomID3;
    private JTextField CourseKey3;
    private JButton applyButton;
    private JPanel DelCours;

    public DelCourse() {
        setContentPane(DelCours);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);




    applyButton.addActionListener(new ActionListener(){


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String startTime = StartTime3.getText();
            String roomID = RoomID3.getText();
            String courseKey = CourseKey3.getText();


            try {
                TransactionMySQL.stmt.executeUpdate("DELETE FROM SCHEDULE_ WHERE coursekey='" + courseKey + "' AND STARTTIME='" + startTime + "' AND roomid='" + roomID + "';");

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            int result = JOptionPane.showConfirmDialog(null, "The data has been updated! \n Do you want to go to the main menu?");
            switch (result) {
                case JOptionPane.YES_OPTION:
                    MM mm = new MM();
                    setVisible(false);
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    System.exit(0);
                    break;
            }

        }
    });



    }}