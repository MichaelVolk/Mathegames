package com.michaelvolk.mathegames

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaelvolk.mathegames.databinding.FragmentKlasse5Binding
import com.michaelvolk.mathegames.klasse5.Klasse5RomanActivity
import com.michaelvolk.mathegames.klasse5.Klasse5ZahlenstrahlActivity


class Klasse5Fragment : Fragment() {
    private var _binding: FragmentKlasse5Binding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentKlasse5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gamezahlenstrahl.setOnClickListener {
            val intent = Intent(activity, Klasse5ZahlenstrahlActivity::class.java)
            (activity as MainActivity?)!!.startActivity(intent)
        }
        binding.gameromannumerals.setOnClickListener {
            val intent = Intent(activity, Klasse5RomanActivity::class.java)
            (activity as MainActivity?)!!.startActivity(intent)
        }
    }
}