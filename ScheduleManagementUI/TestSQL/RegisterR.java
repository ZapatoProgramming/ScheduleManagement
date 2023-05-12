import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterR extends JFrame {
    String ClasroomCap, ClasroomT, Building, ClasroomC;
    private JButton applyButton;
    private JPanel RegR;
    private JTextField clasroomCap;
    private JTextField clasroomT;
    private JTextField building;
    private JTextField clasroomC;


public RegisterR() {

    setContentPane(RegR);
    setTitle("Unified Course Registration Interface");
    setSize(590, 590);
    setVisible(true);

    applyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ClasroomCap = clasroomCap.getText();
            ClasroomT = clasroomT.getText();
            Building = building.getText();
            ClasroomC = clasroomC.getText();


            try {
                TransactionMySQL.stmt.executeUpdate("insert into ROOM(ROOMID, BUILDING, ROOMTYPE, CAPACITY)   values('"+ClasroomC+"', '"+Building+"', "+"'"+ClasroomT+"', '"+ClasroomCap+"');");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            int result =JOptionPane.showConfirmDialog(null, "The data has been updated! \n Do you want to go to the main menu?");
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
