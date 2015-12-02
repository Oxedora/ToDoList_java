package todo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;


//Classes internes de JPanels qui contiendront les listes de tâches sous forme de boutons
public class PanTask extends JPanel{
	private Vector<Task> taskList;
	private String title;
	private JScrollPane scrollButtonTab;
	
	public PanTask(Vector<Task> taskList, String title) {
		super();
		this.taskList = taskList;
		this.title = title;
		this.setLayout(new BorderLayout());
		
		String[] titleT = {this.title};
		String[][] tab = new String[1][taskList.size()+1];
		int cpt = 0;
		for(Task t : this.taskList){
			tab[0][cpt] = t.getTitle()+" "+t.getEndingDate();
			cpt++;
		}
		
		JTable buttonTab = new JTable(tab, titleT);
		buttonTab.getColumn(this.title).setCellRenderer(new ButtonRenderer());
	    buttonTab.getColumn(this.title).setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollButtonTab = new JScrollPane(buttonTab);
		/*
		 * Creation de boutons pour chaque tâche qui affichent leurs informations
		 *  dans le JPanel du centre		
		 */
		this.add(scrollButtonTab, BorderLayout.CENTER);
	}              

}

/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;

  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      JOptionPane.showMessageDialog(button, label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
