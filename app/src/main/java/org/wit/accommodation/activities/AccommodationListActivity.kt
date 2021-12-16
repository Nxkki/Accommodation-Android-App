package org.wit.accommodation.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import timber.log.Timber.i
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.accommodation.R
import org.wit.accommodation.adapters.AccommodationAdapter
import org.wit.accommodation.adapters.AccommodationListener
import org.wit.accommodation.databinding.ActivityAccommodationListBinding
import org.wit.accommodation.main.MainApp
import org.wit.accommodation.models.AccommodationModel

class AccommodationListActivity : AppCompatActivity(), AccommodationListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityAccommodationListBinding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccommodationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = AccommodationAdapter(app.accommodations.findAll(),this)


        loadAccommodations()
        registerRefreshCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        var searchItem=menu?.findItem(R.id.action_search)
        var searchView=searchItem?.actionView as SearchView;

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query?.length!! <1)

                    loadAccommodations()
                else
                    showAccommodations(app.accommodations.filteringPrice(Integer.parseInt(query)))

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText?.length!! >=1)
                    showAccommodations(app.accommodations.filteringPrice(Integer.parseInt(newText)))
                return false
            }
        })
        val closeButton: View? = searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            loadAccommodations()        }


        var searchItemType=menu?.findItem(R.id.action_searchType)
        var searchViewType=searchItemType?.actionView as SearchView;

        searchViewType.setImeOptions(EditorInfo.IME_ACTION_DONE)
        searchViewType.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query?.length!! <1)

                    loadAccommodations()
                else
                    showAccommodations(app.accommodations.filteringType(query))

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length!! >=1)
                    showAccommodations(app.accommodations.filteringType(newText))
                return false
            }
        })
        val closeButtonType: View? = searchViewType.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButtonType?.setOnClickListener {
            loadAccommodations()        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, AccommodationActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAccommodationClick(accommodation: AccommodationModel) {
        val launcherIntent = Intent(this, AccommodationActivity::class.java)
        launcherIntent.putExtra("accommodation_edit", accommodation)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { loadAccommodations() }
    }

    private fun loadAccommodations() {
        showAccommodations(app.accommodations.findAll())
    }

    fun showAccommodations (accommodations: List<AccommodationModel>) {
        binding.recyclerView.adapter = AccommodationAdapter(accommodations, this)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}

