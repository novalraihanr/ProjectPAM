package com.example.projectpam.viewModel

import android.app.ActivityManager.TaskDescription
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class MakananMinumanViewModel : ViewModel() {
    private val database = Firebase.database("https://pamproject-88c82-default-rtdb.asia-southeast1.firebasedatabase.app")

    val makananminuman = mutableStateListOf<MakananMinuman>()
    val orders = mutableStateListOf<MakananMinuman>()
    val history = mutableStateListOf<historyOrder>()

    init {
        getMakananMinuman()
        getOrders(Firebase.auth.currentUser?.displayName.toString())
        getHistory(Firebase.auth.currentUser?.displayName.toString())
    }

    fun getMakananMinuman() {
        database.getReference("makananMinuman").get()
            .addOnCompleteListener { result ->
                if(result.isSuccessful) {
                    result.result.getValue<List<MakananMinuman>>()?.let {
                        makananminuman.clear()
                        makananminuman.addAll(it)
                    }
                }
            }
    }

    fun getOrders(nama : String) {
        database.getReference().child("users").child(nama).child("orders").
                addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            orders.clear()
                            snapshot.getValue<List<MakananMinuman>>()?.let {
                                orders.addAll(it)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.e("MakananMinumanViewModel", error.message)
                        }
                    }
                )
    }

    fun getHistory(nama : String) {
        database.getReference().child("users").child(nama).child("history").
        addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    history.clear()
                    snapshot.getValue<List<historyOrder>>()?.let {
                        history.addAll(it)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MakananMinumanViewModel", error.message)
                }
            }
        )
    }

    fun insertOrder(name: String, price: Int, type: String, description: String, image: String) {;

        val ordersID: Int = if(orders.size == 0) {
            0
        }else {
            orders.last().id
        }

        val newMakmin = MakananMinuman(
            id = ordersID + 1,
            name = name,
            price = price,
            type = type,
            description = description,
            image = image
        )

        database.getReference("users").child(Firebase.auth.currentUser?.displayName.toString()).child("orders").child(ordersID.toString()).setValue(newMakmin.toJson())
    }

    fun addToHistory(name: String, price: Int, image: String){

        val ordersID: Int = if(history.size == 0) {
            0
        }else {
            history.last().id
        }

        val newMakmin = historyOrder(
            id = ordersID + 1,
            names = name,
            total = price,
            image = image
        )

        database.getReference("users").child(Firebase.auth.currentUser?.displayName.toString()).child("history").child(ordersID.toString()).setValue(newMakmin.toJson())
        database.getReference("users").child(Firebase.auth.currentUser?.displayName.toString()).child("orders").removeValue()

    }

    fun increaseQuantity(id: Int){

    }

    fun deleteMakananMinuman(id: Int, name: String) {
        database.getReference().child("makananMinuman").child(id.toString()).removeValue()
    }
}

data class MakananMinuman(
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val type: String = "",
    val description: String = "",
    val quantity: Int = 0,
    val image: String = ""
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "id" to id,
        "name" to name,
        "price" to price,
        "type" to type,
        "description" to description,
        "quantity" to quantity,
        "image" to image
    )
}

data class historyOrder(
    val id: Int = 0,
    val names: String = "",
    val total: Int = 0,
    val image: String = ""
) {
    fun toJson(): Map<String, Any?> = mapOf(
        "id" to id,
        "names" to names,
        "total" to total,
        "image" to image
    )
}