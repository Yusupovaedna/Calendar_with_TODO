package calendar.com.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import calendar.com.ui.CalendarUtils.formattedDate
import androidx.appcompat.app.AppCompatActivity
import calendar.com.ui.CalendarUtils
import calendar.com.ui.WeekViewActivity
import java.time.LocalTime
import java.time.ZoneId

class EventEditActivity : AppCompatActivity() {
    private var eventNameET: EditText? = null
    private var eventDateTV: TextView? = null
    private val eventTimeTV: TextView? = null
    private val calendarView: CalendarView? = null
    private var time: LocalTime? = null
    private val checked: CheckBox? = null
    private var save: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)
        initWidgets()
        time = LocalTime.now(ZoneId.of("Europe/Moscow"))
        eventNameET!!.setBackgroundColor(Color.parseColor("#F9CDD2"))
        eventDateTV!!.text = "Date: " + formattedDate(CalendarUtils.selectedDate!!)
        save!!.isEnabled = false
        eventNameET!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
//                if(s.length() != 0)
//                    field2.setText("");
                if (eventNameET!!.text.toString().replace(" ".toRegex(), "") != "") {
                    save!!.isEnabled = true
                    save!!.setTextColor(Color.parseColor("#B3E4B2"))
                    eventNameET!!.setBackgroundColor(Color.parseColor("#B3E4B2"))
                } else {
                    save!!.isEnabled = false
                    save!!.setTextColor(Color.parseColor("#F9CDD2"))
                    eventNameET!!.setBackgroundColor(Color.parseColor("#F9CDD2"))
                }
            }
        })


//        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));

//        eventDateTV.setText(""+Instant.ofEpochMilli(calendarView.getDate()).atZone(ZoneId.systemDefault()).toLocalDate());
//        eventTimeTV.setText("" + CalendarUtils.formattedTime(time));
    }

    private fun initWidgets() {
        eventNameET = findViewById(R.id.eventNameET)
        eventDateTV = findViewById(R.id.eventDateTV)
        save = findViewById(R.id.save_but)
        //        eventTimeTV = findViewById(R.id.eventTimeTV);
//        calendarView = findViewById(R.id.calendarView);
//        checked =  findViewById(R.id.checkBox2);
    }

    fun saveEventAction(view: View?) {
        val eventName = eventNameET!!.text.toString()
        val newEvent = Event(eventName, CalendarUtils.selectedDate!!, false)
        Event.eventsList.add(newEvent)
        startActivity(Intent(this, WeekViewActivity::class.java))
        finish()
    }

    fun backAction(view: View?) {
        onBackPressed()
    }
}