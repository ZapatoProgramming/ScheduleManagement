import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class roomcheck extends JFrame{
    private JButton allInformationOfAllButton;
    private JButton capacityOfRoomsButton;
    private JButton typeOfRoomsButton;
    private JButton informationOfAnSpecificButton;
    private JPanel roomcheck;
    private JButton BACKButton;

    public roomcheck() {
        setDefaultCloseOperation(3);
        setContentPane(roomcheck);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);

        setVisible(true);

        BACKButton.addActionListener(e -> {
            MM mm = new MM();
            setVisible(false);
        });

        allInformationOfAllButton.addActionListener(e -> {
            try
            {
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("SELECT * FROM room");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }

        });
        capacityOfRoomsButton.addActionListener(e -> {
            try
            {
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("SELECT roomID, capacity FROM room");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });
        typeOfRoomsButton.addActionListener(e -> {
            try
            {
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("SELECT roomID,roomtype FROM room");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });
        informationOfAnSpecificButton.addActionListener(e -> {
            // crear cuadro de diálogo de entrada
            String input = JOptionPane.showInputDialog(this, "Which room?");


            try
            {
                TransactionMySQL.stmt = TransactionMySQL.conn.createStatement();
                ResultSet rs = TransactionMySQL.stmt.executeQuery("SELECT * FROM room where roomID='"+input+"';");
                new ViewWindow(rs);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        });
    }
}
