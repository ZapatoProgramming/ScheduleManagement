import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class CreateReservation  extends JFrame{

    private JTextField CourseKey;
    private JTextField Date;
    private JTextField Name;
    private JTextField RoomID;
    private JTextField StartHour;
    private JTextField Duration;
    private JButton BACKButton;
    private JButton ACEPTARButton;
    private JPanel CreateReservation;



    public CreateReservation() {
        setDefaultCloseOperation(3);
        setContentPane(CreateReservation);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);

        BACKButton.addActionListener(e -> {
            MM mm = new MM();
            setVisible(false);
        });

        ACEPTARButton.addActionListener(e -> {
            String coursekey = CourseKey.getText();
            String Date_Time = Date.getText();
            String Name_ = Name.getText();
            String ROOMID = RoomID.getText();
            LocalTime startTime = LocalTime.parse(StartHour.getText());
            int duration = Integer.parseInt(Duration.getText());
            LocalTime endTime = startTime.plusMinutes(duration);
            try {
                TransactionMySQL.stmt.executeUpdate("INSERT INTO RESERVATION(COURSEKEY,DATE_,STARTTIME,ENDTIME,NAME_,ROOMID,DURATION) VALUES(" + "'" + coursekey + "'," + "'" + Date_Time + "',"+ "'" + startTime + "',"+"'" + endTime + "'," + "'" + Name_ + "'," + "'" + ROOMID + "'," + duration + ");");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            MM mm = new MM();
            setVisible(false);
        });
    }
}
