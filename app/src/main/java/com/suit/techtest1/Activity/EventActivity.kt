package com.suit.techtest1.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.suit.techtest1.Adapter.ListViewAdapter
import com.suit.techtest1.Extension.*
import com.suit.techtest1.Model.Event
import com.suit.techtest1.Persistence.Persistence
import com.suit.techtest1.R
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setListAdapter()
    }

    fun setListAdapter() {
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = ListViewAdapter(Persistence.shared.listEvent) {
            backToChooseActivity(it)
            showInfo(it)
        }
    }

    fun backToChooseActivity(event: Event) {
        val intent = Intent(this@EventActivity, ChooseActionActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        intent.putExtra("info", event)
        startActivity(intent)
        finish()
    }

    fun showInfo(event: Event) {
        val day = event.date.currentDay()
        if ((day%2).isZero() && (day%3).isZero()) {
            showToast("ios")
        } else if ((day%3).isZero()) {
            showToast("android")
        } else if ((day%2).isZero()) {
            showToast("blackberry")
        } else {
            showToast("feature phone")
        }
    }
}
