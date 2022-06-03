package calendar.com.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import calendar.com.ui.Event.Companion.eventsForDate
import androidx.appcompat.app.AppCompatActivity
import calendar.com.ui.EventAdapter
import calendar.com.ui.MainActivity
import calendar.com.ui.Stat
import calendar.com.ui.EventEditActivity
import java.time.LocalDate
import java.util.ArrayList

class Stat : AppCompatActivity() {
    private var eventListView: ListView? = null
    private var eventListView2: ListView? = null
    private lateinit var done: Button
    private lateinit var not_done: Button
    var res_done: Long = 0
    var res_not_done: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)
        eventListView = findViewById(R.id.eventList)
        eventListView2 = findViewById(R.id.eventList2)
        setEventAdpater()
        done = findViewById(R.id.done)
        not_done = findViewById(R.id.not_done)
        res_done = Event.eventsList.stream().filter { x: Event -> x.checked }.count()
        res_not_done = Event.eventsList.stream().filter { x: Event -> !x.checked }.count()
        done.setText(res_done.toString() + "")
        not_done.setText(res_not_done.toString() + "")
    }

    private fun setEventAdpater() {
        //Adapter for today
        val dailyEvents: ArrayList<Event?> = eventsForDate(LocalDate.now())
        if (dailyEvents.isNotEmpty()) {
            val eventAdapter = EventAdapter(applicationContext, dailyEvents)
            eventListView!!.adapter = eventAdapter
        } else {
            val values = arrayOf("Nothing is scheduled")
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, values
            )
            eventListView!!.adapter = adapter
        }
        //Adapter for tomorrow
        val tomorrowEvents: ArrayList<Event?> = eventsForDate(LocalDate.now().plusDays(1))
        if (tomorrowEvents.isNotEmpty()) {
            val eventAdapter = EventAdapter(applicationContext, tomorrowEvents)
            eventListView2!!.adapter = eventAdapter
        } else {
            val values = arrayOf("Nothing is scheduled")
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, values
            )
            eventListView2!!.adapter = adapter
        }
    }

    fun monthAction(view: View?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun statAction(view: View?) {
        startActivity(Intent(this, Stat::class.java))
    }

    fun addAction(view: View?) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }
}