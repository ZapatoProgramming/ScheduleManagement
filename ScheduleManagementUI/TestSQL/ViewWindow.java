import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewWindow extends JFrame {

    public ViewWindow(ResultSet rs){
        // Display the JTable in a JFrame
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        try {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Add columns to the table model
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnLabel(i));
            }

            // Add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        add(new JScrollPane(table));
        pack();
        setVisible(true);

    }
}
