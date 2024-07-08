package com.adamco.menudemoproject

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adamco.menudemoproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contextAdapter: ContextMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        popUpMenuThings()
        contextMenuThings()
    }

    // CONTEXT MENU
    private fun getData() = mutableListOf(
        ContactInfo("John", "+1 432-1234-1231", R.drawable.ic_launcher_foreground),
        ContactInfo("Alex", "+1 932-12134-131", R.drawable.ic_launcher_foreground),
        ContactInfo("Sally", "+1 032-1234-531", R.drawable.ic_launcher_foreground),
        ContactInfo("Mike", "+1 222-1324-1031", R.drawable.ic_launcher_foreground)
    )

    private fun contextMenuThings() {
        contextAdapter = ContextMenuAdapter(getData(), this)
        binding.recyclerView.apply {
            adapter = contextAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        registerForContextMenu(binding.recyclerView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
//        menuInflater.inflate(R.menu.context_menu, menu)  **its inflated in the adapter**
    }

    // POP-UP MENU
    private fun popUpMenuThings() {
        val popUpMenu = PopupMenu(this, binding.popupButton)
        popUpMenu.menuInflater.inflate(R.menu.menu_pop_up, popUpMenu.menu)

        popUpMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.bus -> makeToast("You chose to ride a bus")
                R.id.plane -> makeToast("You chose to ride a plane")
                R.id.car -> makeToast("You chose to ride a car")
                R.id.truck -> makeToast("You chose to ride a truck")
            }
            true
        }

        binding.popupButton.setOnClickListener {
            popUpMenu.show()
        }
    }

    // OPTIONS MENU
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.hat -> {
                makeToast("Hat selected")
                true
            }

            R.id.pants -> {
                makeToast("Jeans selected")
                true
            }

            R.id.shirt -> {
                makeToast("Polo shirt selected")
                true
            }

            R.id.watch -> {
                makeToast("Luxury watch selected")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // MAKE TOAST FUNCTION
    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
