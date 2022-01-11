package com.example.recyclerview_loading_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview_loading_4.adapter.CustomAdapter
import com.example.recyclerview_loading_4.databinding.ActivityMainBinding
import com.example.recyclerview_loading_4.listener.OnBottomClickListener
import com.example.recyclerview_loading_4.model.Member

class MainActivity : AppCompatActivity() {
    private lateinit var bin: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        initViews()

        val members = prepareMemberList()
        refreshAdapter(members)

    }

    private fun initViews() {
        bin.recyclerview.layoutManager = GridLayoutManager(this, 1)
    }

    private fun refreshAdapter(member: List<Member>) {
        val adapter = CustomAdapter(member, object : OnBottomClickListener {
            override fun onBottomListener(position: Int) {
                Toast.makeText(this@MainActivity, "Bottom", Toast.LENGTH_SHORT).show()
            }
        })
        bin.recyclerview.adapter = adapter
    }

    private fun prepareMemberList(): List<Member> {
        val members = ArrayList<Member>()

        for (i in 0..20) {
            if (i % 5 == 0) {
                members.add(Member("Android", "$i", false))
            } else {
                members.add(Member("Android", "$i", true))
            }
        }
        return members
    }
}