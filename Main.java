//package toDoListTeam23;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private static final int APPLET_WIDTH = 800, APPLET_HEIGHT = 600;

	//private JTabbedPane tPane;
	private AddViewPanel addView;
	
	private JTable listViewTable;
	private JScrollPane  listViewTableScrollPane;

	private ToDoList toDoList;
	private TodoListItemTableModel tableModel;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		toDoList = new ToDoList();
		tableModel  = new TodoListItemTableModel(toDoList);		
		
		listViewTable = new JTable(tableModel);
		listViewTableScrollPane = new JScrollPane(listViewTable);

		listViewTable.setFillsViewportHeight(true);

		addView = new AddViewPanel(toDoList, listViewTable);
		
		setLayout(new BorderLayout());

		add(addView, BorderLayout.NORTH);
		add(listViewTableScrollPane);

		setSize(APPLET_WIDTH, APPLET_HEIGHT); // set Applet size
		
		this.setTitle("Team 23 Todo List");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
