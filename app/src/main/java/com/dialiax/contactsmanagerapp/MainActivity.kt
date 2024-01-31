package com.dialiax.contactsmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dialiax.contactsmanagerapp.viewModel.UserViewModel
import com.dialiax.contactsmanagerapp.viewModel.UserViewModelFactory
import com.dialiax.contactsmanagerapp.databinding.ActivityMainBinding
import com.dialiax.contactsmanagerapp.room.User
import com.dialiax.contactsmanagerapp.room.UserDatabase
import com.dialiax.contactsmanagerapp.room.UserRepository
import com.dialiax.contactsmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Room Database
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        userViewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)

        binding.userViewModel = userViewModel

        binding.lifecycleOwner = this

        initRecyclerView()



    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        DisplayUserList()
    }

    private fun DisplayUserList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerview.adapter = MyRecyclerViewAdapter(
                it, {seletedItem:User-> listItemClicked(seletedItem)}
            )
        })
    }

    private fun listItemClicked(seletedItem: User) {

        Toast.makeText(this,"Selected name is ${seletedItem.name}",Toast.LENGTH_SHORT).show()

        userViewModel.initUpdateAndDelete(seletedItem)


    }
}