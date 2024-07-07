package saha.profile.subham.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import saha.profile.subham.R
import saha.profile.subham.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private var _setting : FragmentSettingsBinding? = null
    private val settings get() = _setting!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _setting = FragmentSettingsBinding.inflate(inflater, container, false)
        return settings.root
    }


}