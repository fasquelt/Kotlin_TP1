import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1.Contact
import com.example.tp1.DiffCallback
import com.example.tp1.databinding.ListeContactBinding

class ContactAdapter : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(DiffCallback()) {

    class ContactViewHolder(private val binding : ListeContactBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(c: Contact){
                binding.prenom.setText(c.firstname)
                binding.nom.setText(c.name)
                binding.tel.setText(c.tel)
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ListeContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }




}