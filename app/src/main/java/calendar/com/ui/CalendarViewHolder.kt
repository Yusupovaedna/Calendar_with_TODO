package calendar.com.ui

import android.view.View
import android.widget.TextView
import calendar.com.ui.CalendarAdapter.OnItemListener
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.ArrayList

class CalendarViewHolder(itemView: View, onItemListener: OnItemListener, days: ArrayList<LocalDate?>) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val days: ArrayList<LocalDate?>
    val parentView: View
    val dayOfMonth: TextView
    private val onItemListener: OnItemListener

    init {
        parentView = itemView.findViewById(R.id.parentView)
        dayOfMonth = itemView.findViewById(R.id.cellDayText)
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
        this.days = days
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}