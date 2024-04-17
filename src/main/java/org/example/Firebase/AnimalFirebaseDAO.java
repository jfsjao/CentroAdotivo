package org.example.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import org.example.Classes.Animal;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer; // Importe a classe Consumer corretamente.

public class AnimalFirebaseDAO implements FirebaseDAO<Animal> {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("animals");

    @Override
    public void create(String key, Animal data) {
        // O setValue() é uma operação síncrona e não retorna um ApiFuture no Admin SDK.
        dbRef.child(key).setValue(data, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
    }

    @Override
    public void read(String key, Consumer<Animal> callback) {
        // Note que este método não é bloqueante. Não há necessidade do CountDownLatch aqui.
        dbRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Animal animal = dataSnapshot.getValue(Animal.class);
                callback.accept(animal);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Failed to read data: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void update(String key, Animal data) {
        // Mesmo que create, não precisa de addOnSuccessListener.
        dbRef.child(key).setValue(data, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Data could not be updated " + databaseError.getMessage());
                } else {
                    System.out.println("Data updated successfully.");
                }
            }
        });
    }

    @Override
    public void delete(String key) {
        // Mesmo que create, não precisa de addOnSuccessListener.
        dbRef.child(key).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.err.println("Data could not be deleted " + databaseError.getMessage());
                } else {
                    System.out.println("Data deleted successfully.");
                }
            }
        });
    }
}
