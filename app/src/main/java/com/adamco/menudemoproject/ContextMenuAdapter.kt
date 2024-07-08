package com.adamco.menudemoproject

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adamco.menudemoproject.databinding.ContactItemBinding

class ContextMenuAdapter(private val contact: List<ContactInfo>, private val activity: AppCompatActivity) : RecyclerView.Adapter<ContextMenuAdapter.ContextMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContextMenuViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContextMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContextMenuViewHolder, position: Int) {
        holder.bind(contact[position])
    }

    override fun getItemCount() = contact.size

    inner class ContextMenuViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        init {
            binding.root.setOnCreateContextMenuListener(this)
        }

        fun bind(contactItem: ContactInfo) {
            with(binding) {
                txtName.text = contactItem.name
                txtNumber.text = contactItem.number
                contactImg.setImageResource(contactItem.contactImg)
            }
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            activity.menuInflater.inflate(R.menu.context_menu, menu)
            menu?.findItem(R.id.menu_call)?.setOnMenuItemClickListener(this)
            menu?.findItem(R.id.menu_sms)?.setOnMenuItemClickListener(this)
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_call -> {
                    makeToast("Call ${binding.txtName.text}")
                    true
                }
                R.id.menu_sms -> {
                    makeToast("SMS ${binding.txtName.text}")
                    true
                }
                else -> false
            }
        }

        private fun makeToast(message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
