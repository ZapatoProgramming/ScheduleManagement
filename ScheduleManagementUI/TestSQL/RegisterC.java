import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterC extends JFrame{
    private JTextField courseK;
    private JTextField section;
    private JTextField courseN;
    private JTextField teachN;
    private JButton applyButton;
    private JPanel RegC;
    String CourseK, Section, CourseN, TeachN;
    public RegisterC() {
        setContentPane(RegC);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);

    applyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CourseK = courseK.getText();
            Section = section.getText();
            CourseN = courseN.getText();
            TeachN = teachN.getText();


                try {
                    TransactionMySQL.stmt.executeUpdate("insert into COURSE(COURSEKEY, SECTION, TITLE, TEACHER)   values('"+CourseK+"', "+Section+", "+"'"+CourseN+"', '"+TeachN+"');");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                TransactionMySQL.conn.commit();
                 } catch (SQLException ex) {
                throw new RuntimeException(ex);
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
