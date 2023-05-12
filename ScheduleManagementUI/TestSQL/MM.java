import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class MM extends JFrame  {

    private JButton RegisterCoursebuttom;
    private JButton registerRoomButton;
    private JButton roomCheckButton;
    private JPanel INICIAL;
    private JButton registerSeasonButton;
    private JButton roomReservationButton;
    private JButton scheduleButton;
    private JButton applyOperationsButton;
    private JButton exitButton;
    public int activo = 0;
    public MM() {
        setDefaultCloseOperation(3);
        setContentPane(INICIAL);
        setTitle("Unified Course Registration Interface");
        setSize(590, 590);

        setVisible(true);




        registerSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegisterS registerS = new RegisterS();
                } catch (Exception er) {
                    throw new RuntimeException(er);
                }
                setVisible(false);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        applyOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TransactionMySQL.conn.commit();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        roomReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomReservation roomReservation = new RoomReservation();
                setVisible(false);
            }
        });
        roomCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomcheck Roomcheck = new roomcheck();
                setVisible(false);
            }
        });


        RegisterCoursebuttom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterC registerC = new RegisterC();
                setVisible(false);
            }
        });
        registerRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterR registerR = new RegisterR();
                setVisible(false);
            }
        });
    }

}

