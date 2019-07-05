package pomobox.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;

import pomobox.data.model.BreakTask;

import static pomobox.utils.Query.BREAK_TASK_TABLE;
import static pomobox.utils.Query.CONTENT_BREAK_TASK;
import static pomobox.utils.Query.CREATE_BREAK_TASK_TABLE;
import static pomobox.utils.Query.DB_NAME;
import static pomobox.utils.Query.DB_VERSION;
import static pomobox.utils.Query.DELETE_BREAK_TASK_TABLE;
import static pomobox.utils.Query.DONE_BREAK_TASK;
import static pomobox.utils.Query.ID_BREAK_TASK;
import static pomobox.utils.Query.PROGRESS_BREAK_TASK;
import static pomobox.utils.Query.SELECT_ALL;
import static pomobox.utils.Query.SELECT_BY_ID;
import static pomobox.utils.Query.TARGET_BREAK_TASK;
import static pomobox.utils.Query.TITLE_BREAK_TASK;

public class BreakTaskHelperDB extends SQLiteOpenHelper implements Serializable {

    public BreakTaskHelperDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public int getTaskCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public ArrayList<BreakTask> getAllTasks() {
        ArrayList<BreakTask> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);
        if (cursor.moveToFirst()) {
            do {
                BreakTask newTask = new BreakTask();
                newTask.setTaskId(cursor.getInt(cursor.getColumnIndex(ID_BREAK_TASK)));
                newTask.setIsDone(cursor.getInt(cursor.getColumnIndex(DONE_BREAK_TASK)));
                newTask.setTaskTitle(cursor.getString(cursor.getColumnIndex(TITLE_BREAK_TASK)));
                newTask.setTaskContent(cursor.getString(cursor.getColumnIndex(CONTENT_BREAK_TASK)));
                newTask.setProgressPomo(cursor.getInt(cursor.getColumnIndex(PROGRESS_BREAK_TASK)));
                newTask.setTargetPomo(cursor.getInt(cursor.getColumnIndex(TARGET_BREAK_TASK)));
                arrayList.add(newTask);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }


    public void insertTaskData(BreakTask task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //Id auto increase
        values.put(DONE_BREAK_TASK, task.getIsDone());
        values.put(TITLE_BREAK_TASK, task.getTaskTitle());
        values.put(CONTENT_BREAK_TASK, task.getTaskContent());
        values.put(PROGRESS_BREAK_TASK, task.getProgressPomo());
        values.put(TARGET_BREAK_TASK, task.getTargetPomo());
        db.insert(BREAK_TASK_TABLE,null, values);//return -1 if insert errror
    }

    public int updateTaskData(BreakTask taskData, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DONE_BREAK_TASK, taskData.getIsDone());
        values.put(TITLE_BREAK_TASK, taskData.getTaskTitle());
        values.put(CONTENT_BREAK_TASK, taskData.getTaskContent());
        values.put(PROGRESS_BREAK_TASK, taskData.getProgressPomo());
        values.put(TARGET_BREAK_TASK, taskData.getTargetPomo());
        String selection = ID_BREAK_TASK + " == "+ id;
        return db.update(BREAK_TASK_TABLE,values, selection, null);
    }

    public ArrayList<BreakTask> getTaskByid(String id){
        ArrayList<BreakTask> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_BY_ID + id, null);
        if(cursor != null){
            cursor.moveToFirst();
            BreakTask task = new BreakTask();
            task.setTaskId(cursor.getInt(cursor.getColumnIndex(ID_BREAK_TASK)));
            task.setIsDone(cursor.getInt(cursor.getColumnIndex(DONE_BREAK_TASK)));
            task.setTaskTitle(cursor.getString(cursor.getColumnIndex(TITLE_BREAK_TASK)));
            task.setTaskContent(cursor.getString(cursor.getColumnIndex(CONTENT_BREAK_TASK)));
            task.setProgressPomo(cursor.getInt(cursor.getColumnIndex(PROGRESS_BREAK_TASK)));
            task.setTargetPomo(cursor.getInt(cursor.getColumnIndex(TARGET_BREAK_TASK)));
            taskList.add(task);
            cursor.close();
        }
        return taskList;
    }

    public void deleteTaskData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = ID_BREAK_TASK + " == " + id;
        db.delete(BREAK_TASK_TABLE, selection, null);
    }

    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen()) db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BREAK_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_BREAK_TASK_TABLE);
        onCreate(db);
    }
}
