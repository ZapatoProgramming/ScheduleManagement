import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModCourseProg extends JFrame {
    private JTextField Year2;
    private JTextField Season2;
    private JTextField Semester2;
    private JTextField Duration2;
    private JTextField StartTime2;
    private JTextField Weekday2;
    private JTextField RoomID2;
    private JTextField Section2;
    private JTextField Coursekey2;
    private JButton applyButton;
    private JPanel AddT1;

    public ModCourseProg() {
        setContentPane(AddT1);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);

        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String year = Year2.getText();
                String season = Season2.getText();
                String semester = Semester2.getText();
                String duration = Duration2.getText();
                String startTime = StartTime2.getText();
                String weekday = Weekday2.getText();
                String roomID = RoomID2.getText();
                String section = Section2.getText();
                String courseKey = Coursekey2.getText();


                try {
                    TransactionMySQL.stmt.executeUpdate("UPDATE SCHEDULE_ SET WEEKDAY = '" + weekday + "', ROOMID = '" + roomID + "', SECTION = '" + section + "', STARTTIME =  '" + startTime + "', ENDTIME =  '" + duration + "', SEMESTER =" + semester +",SEASON = '"+season+"',"+ "YEAR_="+year+" WHERE COURSEKEY='" + courseKey + "';");                } catch (SQLException ex) {
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}