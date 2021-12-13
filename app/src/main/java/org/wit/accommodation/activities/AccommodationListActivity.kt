package org.wit.accommodation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.accommodation.R
import org.wit.accommodation.databinding.ActivityAccommodationListBinding
import org.wit.accommodation.main.MainApp
import org.wit.accommodation.adapters.AccommodationAdapter
import org.wit.accommodation.adapters.AccommodationListener
import org.wit.accommodation.models.AccommodationModel

class AccommodationListActivity : AppCompatActivity(), AccommodationListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityAccommodationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = AccommodationAdapter(app.accommodations)
//        binding.recyclerView.adapter = AccommodationAdapter(app.accommodations.findAll())
        binding.recyclerView.adapter = AccommodationAdapter(app.accommodations.findAll(),this)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
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
    override fun onAccommodationClick(accommodation: AccommodationModel) {
        val launcherIntent = Intent(this, AccommodationActivity::class.java)
        launcherIntent.putExtra("accommodation_edit", accommodation)
        startActivityForResult(launcherIntent,0)
    }

}
//class AccommodationAdapter constructor(private var accommodations: List<AccommodationModel>) :
//    RecyclerView.Adapter<AccommodationAdapter.MainHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
//        val binding = CardAccommodationBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return MainHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MainHolder, position: Int) {
//        val accommodation = accommodations[holder.adapterPosition]
//        holder.bind(accommodation)
//    }
//
//    override fun getItemCount(): Int = accommodations.size
//
//    class MainHolder(private val binding : CardAccommodationBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(accommodation: AccommodationModel) {
//
//            binding.accommodationLocation.text = accommodation.location
//            binding.rooms.text = accommodation.rooms
//
//         binding.accommodationPrice.text =  Integer.toString(accommodation.price)
//        }
//    }
//
//}