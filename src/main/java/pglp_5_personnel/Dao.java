package pglp_5_personnel;


import java.util.ArrayList;
import java.util.Map;

public interface Dao<T> {
       void add(T object);
       T get(int id);
      ArrayList<T> getAll();
       void update(T object, Map<String, Object> params);
       void remove(T object);
}
