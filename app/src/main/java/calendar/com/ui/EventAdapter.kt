package calendar.com.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import calendar.com.ui.Stat

class EventAdapter(context: Context, events: List<Event?>?) : ArrayAdapter<Event?>(context, 0, events!!) {
    private val stat: Stat? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val event = getItem(position)
        if (convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.event_cell, parent, false)
        val eventCellTV = convertView!!.findViewById<TextView>(R.id.eventCellTV)
        val checkBox = convertView.findViewById<CheckBox>(R.id.checkBox2)
        val checked = event!!.checked
        //        String eventTitle = event.getName() + " " + CalendarUtils.formattedDate(event.getDate());
        val eventTitle = event.name + " "
        eventCellTV.text = eventTitle
        checkBox.isChecked = checked
        if (checkBox.isChecked) {
            checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#DFECFF"))
        } else {
            checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#5093F5"))
        }
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkBox.isChecked = true
                checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#DFECFF"))
                event.checked = true
            } else {
                checkBox.isChecked = false
                event.checked = false
                checkBox.buttonTintList = ColorStateList.valueOf(Color.parseColor("#5093F5"))
            }
        }
        return convertView
    }
}