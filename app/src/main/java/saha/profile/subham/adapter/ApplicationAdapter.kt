package saha.profile.subham.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import saha.profile.subham.R
import saha.profile.subham.model.App
import saha.profile.subham.profile.Profile
import saha.profile.subham.utils.FilterApplication

class ApplicationAdapter : RecyclerView.Adapter<ApplicationAdapter.ViewHolder>(), Filterable {

    var applicationItems = ArrayList<App>()


    fun updateData(applicationItems: ArrayList<App>) {
        this.applicationItems = applicationItems
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val app_icon = view.findViewById<CircleImageView>(R.id.app_icon)
        val app_name = view.findViewById<TextView>(R.id.app_name)
        val enable_status = view.findViewById<SwitchCompat>(R.id.enable_status)
        fun bindData(appData: App, position: Int) {
            Log.e("APP DATA","Name"+appData.app_name)
            val truncatedAppName = if (appData.app_name.length > 15) {
                "${appData.app_name.substring(0, 15)}..."
            } else {
                appData.app_name
            }
            app_name.text = truncatedAppName
            enable_status.isChecked = appData.status == "Active"

           val url = appData.app_icon
            Picasso.get().load(url).into(app_icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.application_item_row, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  applicationItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(applicationItems[position], position)
    }

    private val filter : FilterApplication ? = null
    var originalList = ArrayList<App>()
    override fun getFilter(): Filter {
        if(filter == null) return FilterApplication(this, originalList)
        return filter
    }
}