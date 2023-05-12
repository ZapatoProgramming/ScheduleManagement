import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



public class RegisterS extends JFrame {



    String Season, Year, Speriod, Eperiod;
    private JTextField season;
    private JTextField year;
    private JTextField speriod;
    private JTextField eperiod;
    private JButton applyButton;
    private JPanel register;

    public RegisterS() {

        setContentPane(register);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);
        setVisible(true);






        applyButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent a) {
                Season = season.getText();
                Year = year.getText();
                Speriod = speriod.getText();
                Eperiod = eperiod.getText();

                if(TransactionMySQL.diaDeLaSemana(Speriod)==1) {
                    try {
                        TransactionMySQL.stmt.executeUpdate("insert into SEASON(SEASON, YEAR_, START_DATE, END_DATE)   values('"+Season+"', "+Year+", "+"'"+Speriod+"', '"+Eperiod+"');");
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

                else JOptionPane.showMessageDialog(null,"Season must start on Monday");

            }
        });

    }
}