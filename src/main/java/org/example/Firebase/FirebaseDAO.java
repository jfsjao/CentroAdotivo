package org.example.Firebase;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.function.Consumer;

public interface FirebaseDAO<T> {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    void create(String key, T data);
    void read(String key, Consumer<T> callback);
    void update(String key, T data);
    void delete(String key);
}
