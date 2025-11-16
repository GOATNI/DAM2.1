package org.iesch.a03_menu_principal.notifications

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

object FCMTokenManager {
    private const val TAG = "FCMTokenManager"

    /**
     * Obtiene el token FCM actual del dispositivo
     */
    suspend fun getCurrentToken(): String? {
        return try {
            val token = FirebaseMessaging.getInstance().token.await()
            Log.d(TAG, "Token FCM obtenido: $token")
            token
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener token FCM", e)
            null
        }
    }

    /**
     * Guarda el token FCM en Firestore asociado al usuario
     *  userId UID del usuario en Firebase Auth
     *  token Token FCM del dispositivo
     */
    suspend fun saveTokenToFirestore(userId: String, token: String) {
        try {
            val firestore = FirebaseFirestore.getInstance()

            // Actualizar el documento del usuario con el token
            firestore.collection("users")
                .document(userId)
                .update(
                    mapOf(
                        "token" to token,
                        "tokenUpdatedAt" to Timestamp.now()
                    )
                )
                .await()

            Log.d(TAG, "Token guardado en Firestore para usuario: $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Error al guardar token en Firestore", e)

            // Si falla el update (porque el documento no existe), intentar set
            try {
                val firestore = FirebaseFirestore.getInstance()
                firestore.collection("users")
                    .document(userId)
                    .set(
                        mapOf(
                            "token" to token,
                            "tokenUpdatedAt" to Timestamp.now()
                        ),
                        SetOptions.merge()
                    )
                    .await()
                Log.d(TAG, "Token guardado en Firestore (con merge) para usuario: $userId")
            } catch (e2: Exception) {
                Log.e(TAG, "Error definitivo al guardar token", e2)
            }
        }
    }

    /**
     * Obtiene el token FCM y lo guarda en Firestore
     * userId UID del usuario en Firebase Auth
     */
    suspend fun refreshAndSaveToken(userId: String) {
        val token = getCurrentToken()
        if (token != null) {
            saveTokenToFirestore(userId, token)
        } else {
            Log.e(TAG, "No se pudo obtener el token FCM")
        }
    }

    /**
     * Elimina el token del usuario al cerrar sesión
     * userId UID del usuario en Firebase Auth
     */
    suspend fun removeTokenFromFirestore(userId: String) {
        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("users")
                .document(userId)
                .update("token", null)
                .await()

            Log.d(TAG, "Token eliminado de Firestore para usuario: $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Error al eliminar token de Firestore", e)
        }
    }
}