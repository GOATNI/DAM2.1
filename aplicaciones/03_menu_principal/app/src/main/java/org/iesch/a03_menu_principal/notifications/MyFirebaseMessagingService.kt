package org.iesch.a03_menu_principal.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCM_Service"
        private const val CHANNEL_ID = "default_channel"
        private const val CHANNEL_NAME = "Notificaciones"
    }

    /**
     * Se llama cuando se genera un nuevo token FCM
     * Esto ocurre en el primer inicio de la app o cuando el token se regenera
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Nuevo token FCM generado: $token")

        // Guardar el token en SharedPreferences (backup local)
        saveTokenToPreferences(token)

        // Guardar el token en Firestore si el usuario está logueado
        saveTokenToFirestore(token)
    }

    /**
     * Guarda el token en Firestore asociado al usuario actual
     */
    private fun saveTokenToFirestore(token: String) {
        // Obtener el usuario actual
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val userId = currentUser.uid
            val firestore = FirebaseFirestore.getInstance()

            // Usar una coroutine para operaciones asíncronas
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    firestore.collection("users")
                        .document(userId)
                        .update(
                            mapOf(
                                "token" to token,
                                "tokenUpdatedAt" to Timestamp.now()
                            )
                        )
                        .addOnSuccessListener {
                            Log.d(TAG, "Token guardado en Firestore para usuario: $userId")
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error al actualizar token, intentando merge", e)

                            // Si falla el update, intentar con merge
                            firestore.collection("users")
                                .document(userId)
                                .set(
                                    mapOf(
                                        "token" to token,
                                        "tokenUpdatedAt" to Timestamp.now()
                                    ),
                                    SetOptions.merge()
                                )
                                .addOnSuccessListener {
                                    Log.d(TAG, "Token guardado en Firestore (merge) para usuario: $userId")
                                }
                                .addOnFailureListener { e2 ->
                                    Log.e(TAG, "Error definitivo al guardar token", e2)
                                }
                        }
                } catch (e: Exception) {
                    Log.e(TAG, "Excepción al guardar token en Firestore", e)
                }
            }
        } else {
            Log.d(TAG, "No hay usuario logueado, token no guardado en Firestore")
        }
    }

    /**
     * Se llama cuando llega una notificación push
     */
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(TAG, "Mensaje recibido desde: ${message.from}")

        // Verificar si el mensaje contiene datos
        if (message.data.isNotEmpty()) {
            Log.d(TAG, "Datos del mensaje: ${message.data}")
            handleDataMessage(message.data)
        }

        // Verificar si el mensaje contiene una notificación
        message.notification?.let {
            Log.d(TAG, "Título: ${it.title}")
            Log.d(TAG, "Cuerpo: ${it.body}")

            showNotification(
                title = it.title ?: "Nueva notificación",
                body = it.body ?: ""
            )
        }
    }

    /**
     * Maneja mensajes de datos personalizados
     */
    private fun handleDataMessage(data: Map<String, String>) {
        val title = data["title"] ?: "Nueva notificación"
        val body = data["body"] ?: ""
        val type = data["type"] ?: "general"

        // Aquí puedes añadir lógica específica según el tipo de notificación
        when (type) {
            "promocion" -> {
                showNotification(title, body)
            }
            "alerta" -> {
                showNotification(title, body)
            }
            else -> {
                showNotification(title, body)
            }
        }
    }

    /**
     * Muestra la notificación en la barra de notificaciones
     */
    private fun showNotification(title: String, body: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Crear canal de notificación para Android 8.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal para notificaciones de la aplicación"
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Intent que se ejecuta al hacer clic en la notificación
        val intent = Intent(this, MenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Construir la notificación
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .build()

        // Mostrar la notificación
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }

    /**
     * Guarda el token FCM en SharedPreferences
     */
    private fun saveTokenToPreferences(token: String) {
        val prefs = getSharedPreferences("fcm_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("fcm_token", token).apply()
        Log.d(TAG, "Token guardado en preferencias")
    }
}