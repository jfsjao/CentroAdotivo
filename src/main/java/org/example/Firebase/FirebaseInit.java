package org.example.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseInit {
    public static void initialize() {
        try (InputStream serviceAccount = FirebaseInit.class.getClassLoader().getResourceAsStream("CentroAdotivo.json")) {  // Corrigi o nome do arquivo
            if (serviceAccount == null) {
                System.err.println("Não foi possível encontrar o arquivo CentroAdotivo.json");
                return;
            }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://centro-de-adocao-default-rtdb.firebaseio.com/")  // Certifique-se de que a URL está correta
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {  // Verifica se já foi inicializado
                FirebaseApp.initializeApp(options);
            }
            System.out.println("Firebase initialized successfully.");
        } catch (IOException e) {
            System.err.println("Falha ao inicializar o Firebase: " + e.getMessage());
        }
    }
}
