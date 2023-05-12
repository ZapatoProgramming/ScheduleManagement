import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomReservation extends JFrame{

    private JButton occupiedRoomAtAButton;
    private JButton freeRoomsAtAButton;
    private JButton createAReservationButton;
    private JButton cancelReservationAtAButton;
    private JPanel roomReservation;
    private JButton BACKButton;


    public RoomReservation(){
        setDefaultCloseOperation(3);
        setContentPane(roomReservation);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);
        occupiedRoomAtAButton.addActionListener(e -> {
            try
            {
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("SELECT * FROM reservation;");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });
        BACKButton.addActionListener(e -> {
            MM mm = new MM();
            setVisible(false);
        });

        freeRoomsAtAButton.addActionListener(e -> {
            try
            {
                String Date_Time = JOptionPane.showInputDialog(this, "Which date? (YYYY-MM-DD)");
                String startime = JOptionPane.showInputDialog(this, "Which hour? (HH:MM:SS)");
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("(select roomid from room) except (select r.roomid from reservation r, schedule_ s where Date_='" + Date_Time + "' and r.STARTTIME='" + startime + "');");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });

        createAReservationButton.addActionListener(e -> {
            CreateReservation createReservation = new CreateReservation();
            setVisible(false);
        });

        cancelReservationAtAButton.addActionListener(e -> {
            try
            {
                String coursekey = JOptionPane.showInputDialog(this, "Which course?");
                String Date_Time = JOptionPane.showInputDialog(this, "Which date? (YYYY-MM-DD)");
                String roomid = JOptionPane.showInputDialog(this, "Which room? (example CN-109)");
                String hour = JOptionPane.showInputDialog(this, "Which hour? (HH:MM:SS)");
                TransactionMySQL.stmt.executeUpdate("Delete from reservation where coursekey='" + coursekey + "'" + " and date_='" + Date_Time + "'"+ " and STARTTIME='" + hour + "'" + " and roomid='" + roomid + "'" + ";");
                JOptionPane.showMessageDialog(this,"Press Back and apply operations");
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });
    }
}
