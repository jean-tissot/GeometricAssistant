package com.example.geometricassistant.ui.polygon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.geometricassistant.databinding.FragmentPolygonBinding

class PolygonFragment : Fragment() {

    private lateinit var polygonViewModel: PolygonViewModel
    private var _binding: FragmentPolygonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        polygonViewModel =
                ViewModelProvider(this).get(PolygonViewModel::class.java)

        _binding = FragmentPolygonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPolygon
        polygonViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}