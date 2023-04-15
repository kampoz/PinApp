package com.example.pinapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltandroomtutorial.R
import com.example.pinapp.adapter.PinAdapter
import com.example.pinapp.db.PinEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DeletePinCallback {

    lateinit var viewModel: MainActivityViewModel
    private val recyclerview: RecyclerView by lazy { findViewById(R.id.recycler) }
    private val addButton: FloatingActionButton by lazy { findViewById(R.id.addButton) }
    private val adapter: PinAdapter by lazy { PinAdapter(this) }
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        addButton.setOnClickListener {
            showAddDialog()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.pinData.observe(this) { list ->
            adapter.pinsList = list.sortedBy { it.name.lowercase() }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDeleteClick(pinEntity: PinEntity) {
        showDeleteDialog(pinEntity)
    }

    override fun onPause() {
        super.onPause()
        dialog?.dismiss()
    }

    private fun showDeleteDialog(pinEntity: PinEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.do_you_want_to_delete_pin))

        val customLayout: View = layoutInflater.inflate(R.layout.dialog_delete_layout, null)
        val pinNameTextView = customLayout.findViewById<TextView>(R.id.dialog_pin_name_tv)
        val pinCodeTextView = customLayout.findViewById<TextView>(R.id.dialog_pin_code_tv)
        pinNameTextView.text = pinEntity.name
        pinCodeTextView.text = pinEntity.code

        builder.setView(customLayout)
        builder.setPositiveButton(getString(R.string.delete)) { dialog: DialogInterface?, which: Int ->
            viewModel.deleteRecord(pinEntity)
        }
        builder.setNeutralButton(getString(R.string.cancel)) { dialog: DialogInterface?, which: Int -> }
        dialog = builder.create()
        dialog?.show()
    }

    private fun showAddDialog() {
        viewModel.generatePin()
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.add_new_pin))

        val customLayout: View = layoutInflater.inflate(R.layout.dialog_add_layout, null)
        val generatedPinTextView = customLayout.findViewById<TextView>(R.id.generated_pin_code_tv)
        val pinNameEditText = customLayout.findViewById<EditText>(R.id.dialog_pin_name_et)

        generatedPinTextView.text = viewModel.pinToAdd

        builder.setView(customLayout)
        builder.setPositiveButton(getString(R.string.add)) { dialog: DialogInterface?, which: Int -> }
        builder.setNeutralButton(getString(R.string.cancel)) { dialog: DialogInterface?, which: Int -> }
        dialog = builder.create()
        dialog?.show()
        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            if (pinNameEditText.text.isNullOrEmpty()) {
                pinNameEditText.error = getString(R.string.name_can_not_be_empty)
            } else {
                viewModel.saveNewPin(pinNameEditText.text.toString())
                dialog?.dismiss()
            }
        }
    }
}