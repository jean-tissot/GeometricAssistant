package com.example.geometricassistant.ui.polygon

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
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
        val nbSidesInput = binding.nbSides;
        if (nbSidesInput != null) {
            val adapter = context?.let { ArrayAdapter<String>(it, R.layout.support_simple_spinner_dropdown_item) }
            adapter?.addAll("4", "8", "16", "32", "64")
            nbSidesInput.adapter = adapter
            nbSidesInput.onItemSelectedListener = NbSideListener()
        }

        // The radius input value
        binding.radius?.addTextChangedListener(RadiusListener())
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class NbSideListener: OnItemSelectedListener {
        override fun onItemSelected(parentView: AdapterView<*>?, selectedView: View?, position: Int, id: Long) {
            val selectedValue = parentView?.getItemAtPosition(position) as String
            polygonViewModel.nbSides = selectedValue.toInt()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            polygonViewModel.nbSides = null
        }
    }

    inner class RadiusListener: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            Log.w("Selecting radius", text.toString())
            try {
                polygonViewModel.radius = text.toString().toInt()
            } catch (e: java.lang.Exception) {
                polygonViewModel.radius = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}