package com.mohamadrizki.depin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mohamadrizki.depin.R
import com.mohamadrizki.depin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ibEconomy.setOnClickListener {
            book(view, ECONOMY)
        }
        binding.ibBusiness.setOnClickListener {
            book(view, BUSINESS)
        }
        binding.ibExecutive.setOnClickListener {
            book(view, EXECUTIVE)
        }
    }

    fun book(view: View, shipClass: String) {
        val mBundle = Bundle()
        mBundle.putString(EXTRA_SHIP_CLASS, shipClass)
        view.findNavController().navigate(R.id.action_navigation_home_to_pemesananActivity, mBundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ECONOMY = "Ekonomi"
        const val BUSINESS = "Bisnis"
        const val EXECUTIVE = "Eksekutif"

        const val EXTRA_SHIP_CLASS = "extra_ship_class"
    }
}