package com.isaiahvonrundstedt.fokus.features.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.preference.Preference
import com.isaiahvonrundstedt.fokus.R
import com.isaiahvonrundstedt.fokus.components.utils.PreferenceManager
import com.isaiahvonrundstedt.fokus.databinding.FragmentNoticesBinding
import com.isaiahvonrundstedt.fokus.features.shared.abstracts.BaseFragment
import com.isaiahvonrundstedt.fokus.features.shared.abstracts.BasePreference

class NoticesFragment : BaseFragment() {
    private var _binding: FragmentNoticesBinding? = null
    private var controller: NavController? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInsets(binding.root, binding.appBarLayout.toolbar)
        controller = Navigation.findNavController(view)

        with(binding.appBarLayout.toolbar) {
            setTitle(R.string.activity_notices)
            setupNavigation(this, R.drawable.ic_outline_arrow_back_24) {
                controller?.navigateUp()
            }
        }
    }

    companion object {

        const val URL_LAUNCHER_ICON_BASE = "https://gcet.edu.om"
        const val URL_NOTIFICATION_SOUND =
            "https://gcet.edu.om"
        const val URL_USER_INTERFACE_ICONS = "https://gcet.edu.om"

        class NoticesFragment : BasePreference() {
            private var controller: NavController? = null

            override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
                setPreferencesFromResource(R.xml.xml_about_notices, rootKey)
            }

            override fun onStart() {
                super.onStart()
                controller = Navigation.findNavController(requireView())

                findPreference<Preference>(PreferenceManager.PREFERENCE_LIBRARIES)
                    ?.setOnPreferenceClickListener {
                        controller?.navigate(R.id.navigation_libraries)
                        true
                    }


                findPreference<Preference>(PreferenceManager.PREFERENCE_NOTIFICATION_SOUND)
                    ?.setOnPreferenceClickListener {
                        CustomTabsIntent.Builder().build()
                            .launchUrl(requireContext(), Uri.parse(URL_NOTIFICATION_SOUND))

                        true
                    }


                findPreference<Preference>(PreferenceManager.PREFERENCE_LAUNCHER_ICON)
                    ?.setOnPreferenceClickListener {
                        CustomTabsIntent.Builder().build()
                            .launchUrl(requireContext(), Uri.parse(URL_LAUNCHER_ICON_BASE))

                        true
                    }

                findPreference<Preference>(PreferenceManager.PREFERENCE_UI_ICONS)
                    ?.setOnPreferenceClickListener {
                        CustomTabsIntent.Builder().build()
                            .launchUrl(requireContext(), Uri.parse(URL_USER_INTERFACE_ICONS))

                        true
                    }

            }
        }
    }
}