package com.example.gmail

import EmailAdapter
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.model.Email

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)

        // Tạo danh sách email mẫu
        val emails = listOf(
            Email("E", "Edurila.com", "12:34 PM", "Best Selling Courses", "Are you looking to Learn Web Designing..."),
            Email("C", "Chris Abad", "11:22 AM", "Campaign Monitor", "Help make Campaign Monitor better..."),
            Email("T", "Tuto.com", "11:04 AM", "8h de formation gratuite", "Photoshop, SEO, Blender..."),
            Email("S", "support", "10:26 AM", "Société OVH", "Suivi de vos services..."),
            Email("M", "Matt from Ionic", "09:45 AM", "Ionic Creator", "Announcing the all-new Creator...")
        )

        emailAdapter = EmailAdapter(emails)
        recyclerView.adapter = emailAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}
