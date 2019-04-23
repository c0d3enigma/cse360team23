import java.time.LocalDate;

public class Task {
	private String taskName;
	private String description;
	private int priority;
	private Status status;
	private LocalDate dueDate;
	
	public Task(String taskName, String description, int priority, Status status, LocalDate dueDate) {
		this.taskName = taskName;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.dueDate = dueDate;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public String[] getDataRow() {
		return new String[] {
				getTaskName(),
				getDescription(),
				getStatus().toString(),
				getDueDate().toString(),
				Integer.toString(getPriority())
		};
	}
	
}
