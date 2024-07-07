package saha.profile.subham.utils

import android.util.Log
import android.widget.Filter
import saha.profile.subham.adapter.ApplicationAdapter
import saha.profile.subham.model.App
import java.util.Locale

class FilterApplication(
    private val applicationAdapter: ApplicationAdapter,
    private val applicationList: ArrayList<App>
) : Filter() {
    override fun performFiltering(searching: CharSequence?): FilterResults {
        val filter = FilterResults()

        if(!searching.isNullOrEmpty()) {
            val queryString = searching.toString().trim().lowercase()
            Log.e("SEARCH",""+queryString)
            val filterApplicationList = ArrayList<App>()
            val filteredList = applicationList.filter { app ->
                app.app_name.lowercase().startsWith(queryString)
            }
            filteredList.forEach { filterApp->
                filterApplicationList.add(filterApp)
            }
            filter.apply {
                count = filterApplicationList.size
                values = filterApplicationList
            }

        } else {
            filter.apply {
                count = applicationList.size
                values = applicationList
            }
        }

        return filter
    }

    override fun publishResults(p0: CharSequence?, results: FilterResults?) {
        applicationAdapter.updateData(results?.values as ArrayList<App>)
    }
}