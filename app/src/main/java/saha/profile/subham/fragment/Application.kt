package saha.profile.subham.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import saha.profile.subham.R
import saha.profile.subham.adapter.ApplicationAdapter
import saha.profile.subham.databinding.FragmentApplicationBinding
import saha.profile.subham.model.ApplicationData
import saha.profile.subham.viewmodel.ProfileApplicationViewModel

class Application : Fragment() {

    private var _application: FragmentApplicationBinding? = null
    private val application get() = _application!!
    private lateinit var applicationAdapter: ApplicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       //val view = inflater.inflate(R.layout.fragment_application, container, false)
        _application = FragmentApplicationBinding.inflate(inflater, container, false)

        initViewModel()

        searchByTyping()

        initViewModelData()

        application.dataLoading.visibility = View.VISIBLE
        
        return application.root
    }



    private fun initViewModel() {
        application.applicationList.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        application.applicationList.addItemDecoration(decoration)

        applicationAdapter = ApplicationAdapter()
        application.applicationList.adapter = applicationAdapter
    }

    private fun initViewModelData() {
        val viewModel = ViewModelProvider(this)[ProfileApplicationViewModel::class.java]
        viewModel.applicationListObserver().observe(this, Observer<ApplicationData> {
            if(it != null) {
                applicationAdapter.updateData(it.data.app_list)
                applicationAdapter.originalList = it.data.app_list
                application.dataLoading.visibility = View.GONE
                if (it.data.app_list.size > 0) {
                    application.noDataLayout.visibility = View.GONE
                } else {
                    application.noDataLayout.visibility = View.VISIBLE
                }
            } else {
                Log.e("ERROR","DATA FETCH ISSUE");
                application.dataLoading.visibility = View.GONE
                application.noDataLayout.visibility = View.VISIBLE
            }
        })
        viewModel.applications()
    }

    fun showNoData() {
        application.noDataLayout.visibility = View.VISIBLE
    }

    private fun searchByTyping() {
        application.searchBy.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val query = string.toString().trim()
                applicationAdapter.filter?.filter(query)
                if(!query.isNullOrEmpty()) {
                    Log.e("HERE","SOME STRING")
                    Log.e("HERE","SOME STRING"+applicationAdapter.itemCount)
                    if(applicationAdapter.itemCount == 0) {
                        application.noDataLayout.visibility = View.VISIBLE
                    } else {
                        application.noDataLayout.visibility = View.GONE
                    }
                } else {
                    Log.e("HERE","NO STRING")
                    application.noDataLayout.visibility = View.GONE
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }


}