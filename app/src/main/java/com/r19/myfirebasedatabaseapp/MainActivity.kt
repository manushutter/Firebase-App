package com.r19.myfirebasedatabaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var editTextName:EditText? = null
    var editTextEmail:EditText? = null
    var editTextIdNumber:EditText? = null
    var buttonSave:Button? = null
    var buttonView:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.mEdtName)
        editTextEmail = findViewById(R.id.mEdtEmail)
        editTextIdNumber = findViewById(R.id.mEdtId)
        buttonSave = findViewById(R.id.mBtnSave)
        buttonView = findViewById(R.id.mBtnView)
        //set the onclick listeners for button save and button view
        buttonSave!!.setOnClickListener {
           //Start receiving data from the user
            var name = editTextName!!.text.toString().trim()
            var email= editTextEmail!!.text.toString().trim()
            var idNumber = editTextIdNumber!!.text.toString().trim()
            //Check if the user is submitting empty fields
            if (name.isEmpty()|| email.isEmpty() || idNumber.isEmpty()){
                Toast.makeText(this, "please fill all inputs",
                Toast.LENGTH_LONG).show()
            }else{
                //Proceed to save the received data to the cloud
                var id = System.currentTimeMillis().toString()
                var userData = User(name,email,idNumber,id)
                //Create a table in our cloud called users

                var ref = FirebaseDatabase.getInstance().getReference().child("User/$id")
                //Finally push the data to the cloud
                ref.setValue(userData).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(this, "User saved successfully",
                                                                Toast.LENGTH_LONG).show()
                    }else{
                       Toast.makeText(this, "User saving failed",
                            Toast.LENGTH_LONG).show()
                    }

                }
            }

        }
        buttonView!!.setOnClickListener {
        startActivity(Intent(this,USersActivity::class.java))

        }
    }
}