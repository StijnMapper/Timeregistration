package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myapplication.ui.GeofenceBroadcastReceiver;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

public class GeofenceHelper {
    private Context context;
    private GeofencingClient geofencingClient;
    private PendingIntent geofencePendingIntent;

    public GeofenceHelper(Context context) {
        this.context = context;
        geofencingClient = LocationServices.getGeofencingClient(context);
        geofencePendingIntent = createGeofencePendingIntent();
    }

    /**
     * Maakt een geofence met de opgegeven locatiegegevens en voegt deze toe aan de geofencingClient.
     *
     * @param geofenceId Een unieke ID voor het geofence.
     */
    @SuppressLint("MissingPermission")
    public void createGeofence(double latitude, double longitude, float radius, String geofenceId) {
        Geofence geofence = new Geofence.Builder()
                .setRequestId(geofenceId)
                .setCircularRegion(latitude, longitude, radius)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .build();
        // Maak een GeofencingRequest-object met het geofence-object en trigger-instellingen
        GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                .addGeofence(geofence)
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .build();
        // Voeg het geofence toe aan de geofencingClient
        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
                .addOnSuccessListener(task -> {
                    Log.d(GeofenceHelper.class.getSimpleName(), " geofence created successfully");
                    Intent geofenceIntent = new Intent("com.example.myapp.GEOFENCE_EVENT");
                    // Voeg eventuele extra gegevens toe aan de intent
                    context.sendBroadcast(geofenceIntent);
                })
                .addOnFailureListener(task -> {
                    Log.d(GeofenceHelper.class.getSimpleName(), " geofence not created");
                });
    }

    /**
     * Maakt een PendingIntent-object dat wordt gebruikt om geofence-gebeurtenissen naar de GeofenceBroadcastReceiver te sturen.
     *
     * @return Het gemaakte PendingIntent-object.
     */
    private PendingIntent createGeofencePendingIntent() {
        Intent intent = new Intent(context, GeofenceBroadcastReceiver.class);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }

}
