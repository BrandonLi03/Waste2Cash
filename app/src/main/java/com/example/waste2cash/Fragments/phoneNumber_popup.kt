package com.example.waste2cash.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.waste2cash.Database.DatabaseHelper
import com.example.waste2cash.R

class phoneNumber_popup : DialogFragment() {

    private lateinit var databaseHelper: DatabaseHelper
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(requireContext())
        userId = arguments?.getInt("userId", -1) ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_number_popup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputNewPhoneNumber = view.findViewById<EditText>(R.id.et_phoneNumber)
        val change_btn = view.findViewById<Button>(R.id.btn_change)
        val cancel_btn = view.findViewById<Button>(R.id.btn_cancel)

        change_btn.setOnClickListener {
            val newPhoneNumber = inputNewPhoneNumber.text.toString()
            if (newPhoneNumber.isNotEmpty()) {
                databaseHelper.updatePhoneNumber(userId, newPhoneNumber)
                dismiss()
            }
        }

        cancel_btn.setOnClickListener{
            dismiss()
        }
    }
}