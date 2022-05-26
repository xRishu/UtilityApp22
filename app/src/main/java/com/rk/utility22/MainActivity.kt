package com.rk.utility22

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.rk.utility22.MainViewModel.MainViewModel
import com.rk.utility22.databinding.ActivityMainBinding
import com.rk.utility22.repository.repository
import com.rk.utility22.utils.MainViewModelFactory
var id=0
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnUpdate.setOnClickListener {
            val repository = repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getPost()

            viewModel.myResponse.observe(this, Observer { response ->

                if (response.isSuccessful) {
                    response.body()?.forEach {
                        val alpha_two_code = it.alpha_two_code.toString()
                        val domains = it.domains.toString()
                        val country = it.country.toString()
                        val stateProvince = it.stateProvince.toString()
                        val webPages = it.webPages.toString()
                        val name = it.name.toString()
                        val id1=++id

                        updateFireStore(
                            alpha_two_code,
                            domains,
                            country,
                            stateProvince,
                            webPages,
                            name,
                            id1
                        )
                    }
                } else {
                    Log.d("Response", response.errorBody().toString())
                }
            })
        }

    }

    private fun updateFireStore(
        alpha_two_code: String,
        domains: String,
        country: String,
        stateProvince: String,
        webPages: String,
        name: String,
        id1: Int
    ) {
        val db = FirebaseFirestore.getInstance()
        val member: MutableMap<String, Any> = HashMap()
        member["alpha_two_code"] = alpha_two_code
        member["name"] = name
        member["domains"] = domains
        member["country"] = country
        member["stateProvince"] = stateProvince
        member["webPages"] = webPages


        db.collection("members")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    db.collection("Members").document(document.id).delete().addOnSuccessListener {
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error Deleting documents: ", exception)
            }
        db.collection("members").document("$id")
            .set(member)
            .addOnSuccessListener {
                Toast.makeText(
                    this@MainActivity,
                    "Record Updated successfully ",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@MainActivity, "Failed to Update Records ", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}