package com.example.geometricassistant.ui.polygon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geometricassistant.R
import com.example.geometricassistant.databinding.FragmentPolygonBinding

class PolygonFragment : Fragment() {

    private lateinit var polygonViewModel: PolygonViewModel
    private var _binding: FragmentPolygonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        polygonViewModel = ViewModelProvider(this).get(PolygonViewModel::class.java)

        _binding = FragmentPolygonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // The result value
        val cutValueLeft: TextView = binding.cutValueLeft
        val cutValueRight: TextView = binding.cutValueRight
        polygonViewModel.cutValue.observe(viewLifecycleOwner) {
            cutValueLeft.text = it
            cutValueRight.text = it
        }

        // The side number input value
        val spinner = binding.nbSides;
        if (spinner != null) {
            val adapter = context?.let { ArrayAdapter<String>(it, R.layout.support_simple_spinner_dropdown_item) }
            adapter?.addAll("4", "8", "16", "32", "64")
            spinner.adapter = adapter
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}