import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddTime extends JFrame {
    private JTextField Year1;
    private JTextField Season1;
    private JTextField Semester1;
    private JTextField Duration1;
    private JTextField StartTime1;
    private JTextField Weekday1;
    private JTextField RoomID1;
    private JTextField Section1;
    private JTextField Coursekey1;
    private JButton applyButton;
    private JPanel AddT1;

    public AddTime() {
        setContentPane(AddT1);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);

        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String year = Year1.getText();
                String season = Season1.getText();
                String semester = Semester1.getText();
                String duration = Duration1.getText();
                String startTime = StartTime1.getText();
                String weekday = Weekday1.getText();
                String roomID = RoomID1.getText();
                String section = Section1.getText();
                String courseKey = Coursekey1.getText();


                    try {
                        TransactionMySQL.stmt.executeUpdate("UPDATE SCHEDULE_ SET WEEKDAY = '" + weekday + "', ROOMID = '" + roomID + "', SECTION = '" + section + "', STARTTIME =  '" + startTime + "', ENDTIME =  '" + duration + "', SEMESTER =" + semester +",SEASON = '"+season+"',"+ "YEAR_="+year+" WHERE COURSEKEY='" + courseKey + "';");                    } catch (SQLException ex) {
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
    }
}