import java.util.ArrayList;

public class Category {
  private String mName;
  private static ArrayList<Category> instances = new ArrayList<Category>();
  private int mId;
  private ArrayList<Task> mTasks;

  public Category (String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
    mTasks = new ArrayList<Task>();
  }

  public static void clear() {
    instances.clear();
  }

  public String getName() {
    return mName;
  }

  public static ArrayList<Category> all() {
    return instances;
  }

  public int getId() {
    return mId;
  }

  public static Category find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public ArrayList<Task> getTasks() {
    return mTasks;
  }

  public void addTask(Task task) {
    mTasks.add(task);
  }

}
