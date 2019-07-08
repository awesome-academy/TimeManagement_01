package pomobox.utils;

public class Query {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "pomobox.db";
    public static final String BREAK_TASK_TABLE = "BreakTaskTable";
    
    //Columns in break task
    public static final String ID_BREAK_TASK = "BreakTaskId";
    public static final String DONE_BREAK_TASK = "BreakTaskDone";
    public static final String TITLE_BREAK_TASK = "BreakTaskTitle";
    public static final String CONTENT_BREAK_TASK = "BreakTaskContent";
    public static final String PROGRESS_BREAK_TASK = "BreakTaskProgress";
    public static final String TARGET_BREAK_TASK = "BreakTaskTarget";
    
    //Query break task
    public static final String CREATE_BREAK_TASK_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ BREAK_TASK_TABLE +"("
                    + ID_BREAK_TASK +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    + DONE_BREAK_TASK +" INTEGER(1),"
                    + TITLE_BREAK_TASK +" VARCHAR(30),"
                    + CONTENT_BREAK_TASK +" VARCHAR(200),"
                    + PROGRESS_BREAK_TASK +" INTEGER(8),"
                    + TARGET_BREAK_TASK +" INTEGER(8))";

    public static final String DELETE_BREAK_TASK_TABLE = "DROP TABLE IF EXISTS " + BREAK_TASK_TABLE;
    public static final String SELECT_ALL = "SELECT * FROM "+ BREAK_TASK_TABLE;
    public static final String SELECT_BY_ID = "SELECT * FROM "+ BREAK_TASK_TABLE +" WHERE "+ ID_BREAK_TASK +" = ";
}
