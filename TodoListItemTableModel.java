import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TodoListItemTableModel extends AbstractTableModel {
	private String[] columnNames = new String[] { "Task", "Description", "Progress", "Due Date", "Priority" };
	private Object[][] data;

	public TodoListItemTableModel(ToDoList listData) {
		ArrayList<Task> tasks = listData.getTasks();

		data = new String[tasks.size()][columnNames.length];

		for (int ix = 0; ix < tasks.size(); ix++) {
			data[ix] = tasks.get(ix).getDataRow();
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
}