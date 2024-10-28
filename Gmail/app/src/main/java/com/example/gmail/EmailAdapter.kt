import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.R
import com.example.gmail.model.Email


class EmailAdapter(private val emails: List<Email>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emails[position]
        holder.bind(email)
    }

    override fun getItemCount() = emails.size

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvInitial: TextView = itemView.findViewById(R.id.tvInitial)
        private val tvSenderName: TextView = itemView.findViewById(R.id.tvSenderName)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        private val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        private val tvSummary: TextView = itemView.findViewById(R.id.tvSummary)

        fun bind(email: Email) {
            tvInitial.text = email.senderInitial
            tvSenderName.text = email.senderName
            tvTime.text = email.time
            tvSubject.text = email.subject
            tvSummary.text = email.summary
        }
    }

}
