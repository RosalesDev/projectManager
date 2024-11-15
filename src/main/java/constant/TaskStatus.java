package constant;

import java.util.EnumSet;

public enum TaskStatus {
	PENDING, STARTED, COMPLETED;

	public static boolean isAllowedStatus(TaskStatus status) {
		EnumSet<TaskStatus> allowedStatuses = EnumSet.allOf(TaskStatus.class);
		return allowedStatuses.contains(status);
	}
}