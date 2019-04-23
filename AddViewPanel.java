
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddViewPanel extends JPanel {
	private ToDoList todoList;
	private JButton button1;
	private JTextField taskName;
	private JTextField descriptionText;
	private JTextField dateText;
	private JTextField priorityNumber;
	private JTextArea fieldEntry;
	private String[] progressState;
	JComboBox<String> progressSelect;
	String[] progressList;
	JPanel taskEntryPanel;
	private JTextField infoText;
	private JTable todoListTabel;

	public AddViewPanel(ToDoList toDoList, JTable todoListTabel) {

		this.todoListTabel = todoListTabel;

		JPanel upperSplitPanel = new JPanel();
		JPanel rightSplitPanel = new JPanel();
		this.setLayout(new BorderLayout());

		this.todoList = toDoList;
		taskEntryPanel = new JPanel();
		GridLayout addViewLayout = new GridLayout(6, 2);
		BorderLayout upperSplitPanelLayout = new BorderLayout();

		upperSplitPanel.setLayout(upperSplitPanelLayout);
		taskEntryPanel.setLayout(addViewLayout);
		infoText = new JTextField();
		infoText.setBorder(null);
		infoText.setEditable(false);
		infoText.setBackground(this.getBackground());
		infoText.setForeground(Color.red);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		button1 = new JButton("Add a task");
		buttonPanel.add(button1);
		button1.addActionListener(new ButtonListener());

		upperSplitPanel.add(infoText, BorderLayout.NORTH);
		upperSplitPanel.add(taskEntryPanel, BorderLayout.CENTER);
		upperSplitPanel.add(buttonPanel, BorderLayout.SOUTH);

		progressState = new String[] { "Not Started", "In Progress", "Completed" };
		progressSelect = new JComboBox<>(progressState);

		JLabel taskLabel = new JLabel("Enter task:");
		taskName = new JTextField(5);
		JLabel descriptionLabel = new JLabel("Enter in description:");
		descriptionText = new JTextField(5);
		JLabel dateLabel = new JLabel("Enter Date(mm/dd/yyyy):");
		dateText = new JTextField(5);
		JLabel priorityLabel = new JLabel("Priority");
		priorityNumber = new JTextField(2);
		JLabel progressLabel = new JLabel("Progress");
		progressSelect.setEditable(false);
		progressSelect.setEnabled(false);

		taskEntryPanel.add(taskLabel);
		taskEntryPanel.add(taskName);
		taskEntryPanel.add(descriptionLabel);
		taskEntryPanel.add(descriptionText);

		taskEntryPanel.add(dateLabel);
		taskEntryPanel.add(dateText);

		taskEntryPanel.add(priorityLabel);
		taskEntryPanel.add(priorityNumber);

		taskEntryPanel.add(progressLabel);
		taskEntryPanel.add(progressSelect);

		taskEntryPanel.add(buttonPanel);

		fieldEntry = new JTextArea("No Task");
		fieldEntry.setEditable(false);
		fieldEntry.setBackground(Color.white);
		rightSplitPanel.setLayout(new BorderLayout());

		// setup lower Panel
		JScrollPane scrollPane = new JScrollPane(fieldEntry);

		rightSplitPanel.add(scrollPane);
		// Splits the pane for task entry and display area on panel
		JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperSplitPanel, rightSplitPanel);
		sPane.setDividerLocation(300);
		add(sPane);

	}

	private void UpdateTodoListTable() {
		todoListTabel.setModel(new TodoListItemTableModel(todoList));		
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			infoText.setText("");

			int newPriority = 0;							
			String enteredTaskName;
			String description;
			LocalDate date = null;							
			

			if (allFieldsHaveValues()) {
				try {
					newPriority = Integer.parseInt(priorityNumber.getText());

				} catch (NumberFormatException nfe) {
					infoText.setText("Please enter a number for priority");
					taskEntryPanel.repaint();
					return;

				}
				
				try {
					date = LocalDate.parse(
							dateText.getText()
							.replace('/', '-')
							.replace('.', '-'));
				}
				catch (DateTimeParseException dtpe){
					infoText.setText("Please enter a date in the format YYYY/MM/DD");
					taskEntryPanel.repaint();
					return;
				}

			}

			
			enteredTaskName = taskName.getText().trim();
			description = descriptionText.getText().trim();

			
			Task newList = new Task(enteredTaskName, description, newPriority, Status.NOT_STARTED, date);

			todoList.addTask(newList);


			UpdateTodoListTable();
		}

	}

	private Boolean allFieldsHaveValues() {
		Boolean isValid = true;

		if (taskName.getText().isEmpty() || descriptionText.getText().isEmpty() || dateText.getText().isEmpty()
				|| priorityNumber.getText().isEmpty()) {
			infoText.setText("Please enter all fields.");
			isValid = false;
		}

		return isValid;
	}
}