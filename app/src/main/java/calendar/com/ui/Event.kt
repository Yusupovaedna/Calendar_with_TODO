package calendar.com.ui

import java.time.LocalDate
import java.time.LocalTime
import java.util.ArrayList

class Event     //        this.time = time;
    (var name: String, var date: LocalDate, var checked: Boolean) {
    var time: LocalTime? = null

    companion object {
        @JvmField
        var eventsList = ArrayList<Event>()
        @JvmStatic
        fun eventsForDate(date: LocalDate): ArrayList<Event?> {
            val events = ArrayList<Event?>()
            for (event in eventsList) {
                if (event.date == date) events.add(event)
            }
            return events
        }
    }
}