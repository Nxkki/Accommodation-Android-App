package org.wit.accommodation.activities

import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.accommodation.R
import org.wit.accommodation.databinding.ActivityAccommodationListBinding
import org.wit.accommodation.databinding.CardAccommodationBinding
import org.wit.accommodation.main.MainApp
import org.wit.accommodation.models.AccommodationModel

class AccommodationListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityAccommodationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = AccommodationAdapter(app.accommodations)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, AccommodationActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
class AccommodationAdapter constructor(private var accommodations: List<AccommodationModel>) :
    RecyclerView.Adapter<AccommodationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardAccommodationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val accommodation = accommodations[holder.adapterPosition]
        holder.bind(accommodation)
    }

    override fun getItemCount(): Int = accommodations.size

    class MainHolder(private val binding : CardAccommodationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: AccommodationModel) {

            binding.accommodationLocation.text = accommodation.location
            binding.rooms.text = accommodation.rooms

         binding.accommodationPrice.text =  Integer.toString(accommodation.price)
        }
    }

}