package com.example.okayme.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.example.okayme.R


class ArticleFragment : Fragment() {

    private lateinit var add_btn:Button
    private lateinit var addFirstName_et: EditText
    private lateinit var addLastName_et:EditText
    private lateinit var addAge_et:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article, container, false)


        return view
    }


}