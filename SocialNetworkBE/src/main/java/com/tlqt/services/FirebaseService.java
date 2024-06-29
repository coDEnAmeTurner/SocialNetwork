/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Admin
 */
@Service
public class FirebaseService {

    public void addUser(Map<String, Object> dataMap) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(); // Automatically generates a new document ID
        String docId = docRef.getId();

        dataMap.put("createdAt", Timestamp.now());
        dataMap.put("id", docId);

        docRef.set(dataMap);
    }
}
