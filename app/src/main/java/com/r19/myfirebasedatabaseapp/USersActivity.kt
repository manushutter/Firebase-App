package com.r19.myfirebasedatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class USersActivity : AppCompatActivity() {
    var mylist:ListView ? = null
    var adapter:CustomAdapter? = null
    var users:ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        mylist = findViewById(R.id.mListPeople)
        users = ArrayList()
        adapter = CustomAdapter(this,users!!)
        var ref = FirebaseDatabase.getInstance().getReference().child("User")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                users!!.clear()
                for (snap in snapshot.children){
                    var user = snap.getValue(User::class.java)
                    users!!.add(user!!)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Please pay",
                                Toast.LENGTH_LONG).show()
            }
        })
        mylist!!.adapter = adapter
    }
}