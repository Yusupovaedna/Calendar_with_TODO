package calendar.com.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import calendar.com.ui.CalendarUtils.monthYearFromDate
import calendar.com.ui.CalendarUtils.daysInMonthArray
import androidx.appcompat.app.AppCompatActivity
import calendar.com.ui.CalendarAdapter.OnItemListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import java.time.LocalDate

class MainActivity : AppCompatActivity(), OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidgets()
        CalendarUtils.selectedDate = LocalDate.now()
        setMonthView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
    }

    private fun setMonthView() {
        monthYearText!!.text = monthYearFromDate(CalendarUtils.selectedDate!!)
        val daysInMonth = daysInMonthArray(CalendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
    }

    fun previousMonthAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if (date != null) {
            CalendarUtils.selectedDate = date
            setMonthView()
        }
    }

    fun weeklyAction(view: View?) {
        startActivity(Intent(this, WeekViewActivity::class.java))
    }

    fun statAction(view: View?) {
        startActivity(Intent(this, Stat::class.java))
    }

    fun addAction(view: View?) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }
}