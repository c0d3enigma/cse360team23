import java.util.ArrayList;

public class ToDoList {
	private ArrayList<Task> tasks;
	
	public ToDoList() {
		this.tasks = new ArrayList<Task>();
	}
	
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public ArrayList<Task> getUnfinishedTasks() {
		ArrayList<Task> unfinishedTasks = new ArrayList<Task>();
		for (int index = 0; index < this.tasks.size(); index++) {
			Task task = this.tasks.get(index);
			if (task.getStatus() != Status.FINISHED) {
				unfinishedTasks.add(task);
			}
		}
		return unfinishedTasks;
	}
	
	public ArrayList<Task> getFinishedTasks() {
		ArrayList<Task> finishedTasks = new ArrayList<Task>();
		for (int index = 0; index < this.tasks.size(); index++) {
			Task task = this.tasks.get(index);
			if (task.getStatus() == Status.FINISHED) {
				finishedTasks.add(task);
			}
		}
		return finishedTasks;
	}
	
	private int getTaskIndex(String taskDescription) {
		int taskIndex = -1;
		int currentIndex = 0;
		while (taskIndex < 0 && currentIndex < this.tasks.size()) {
			if (this.tasks.get(currentIndex).getDescription().equals(taskDescription)) {
				taskIndex = currentIndex;
			}
			currentIndex++;
		}
		return taskIndex;
	}
	
	public boolean containsTask(String taskDescription) {
		return this.getTask(taskDescription) != null;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public Task getTask(String taskDescription) {
		Task task = null;
		int taskIndex = this.getTaskIndex(taskDescription);
		if (taskIndex >= 0) {
			task = this.tasks.get(taskIndex);
		}
		return task;
	}
	
	public boolean deleteTask(String description) {
		boolean successful = false;
		if (this.containsTask(description)) {
			this.tasks.remove(this.getTaskIndex(description));
			successful = true;
		}
		return successful;
	}
	
	public boolean completeTask(String description) {
		boolean successful = false;
		if (this.containsTask(description)) {
			this.getTask(description).setStatus(Status.FINISHED);
			successful = true;
		}
		return successful;
	}
}