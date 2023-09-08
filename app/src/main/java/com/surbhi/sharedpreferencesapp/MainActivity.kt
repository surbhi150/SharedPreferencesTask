package com.surbhi.sharedpreferencesapp

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.surbhi.sharedpreferencesapp.databinding.ActivityMainBinding
import com.surbhi.sharedpreferencesapp.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {

   // lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var binding: ActivityMainBinding
    var colorCode1: String = ""
    var colorCode2: String = ""
    var number: Int = 0
    lateinit var recyclerView: RecyclerViewClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SingletonObj.sharedPrefsClass.initSharedPreference(this)

        colorCode1 = SingletonObj.sharedPrefsClass.getString("color1")
        colorCode2 = SingletonObj.sharedPrefsClass.getString("color2")
        number = SingletonObj.sharedPrefsClass.getInt("number")
        recyclerView = RecyclerViewClass(number, colorCode1, colorCode2)
       // sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        binding.lvcolor.adapter = recyclerView
        binding.lvcolor.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            var dialog = Dialog(this@MainActivity)
            var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.show()
            dialogBinding.etClr1.setOnClickListener {
                ColorPickerDialog
                    .Builder(this)                        // Pass Activity Instance
                    .setTitle("Pick Theme")            // Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                    .setDefaultColor("#ffffff")     // Pass Default Color
                    .setColorListener { color, colorHex ->
                        dialogBinding.etClr1.setText(colorHex)    // Handle Color Selection
                        System.out.println("color $color colorHex $colorHex")
                    }
                    .show()
            }
            dialogBinding.etClr2.setOnClickListener {
                ColorPickerDialog
                    .Builder(this)                        // Pass Activity Instance
                    .setTitle("Pick Theme")            // Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                    .setDefaultColor("#ffffff")     // Pass Default Color
                    .setColorListener { color, colorHex ->
                        dialogBinding.etClr2.setText(colorHex)    // Handle Color Selection
                        System.out.println("color $color colorHex $colorHex")
                    }
                    .show()

            }
            dialogBinding.btnSave.setOnClickListener {
                if (dialogBinding.etClr1.text.toString().isNullOrBlank()) {
                    dialogBinding.etClr1.error = "Enter Color Value"
                } else if (
                    dialogBinding.etClr2.text.toString().isNullOrBlank()
                ) {
                    dialogBinding.etClr2.error = "Enter color Value"
                } else if (
                    dialogBinding.etNumber.text.toString().isNullOrBlank()
                ) {
                    dialogBinding.etNumber.error = "Enter Number"
                } else {
                    SingletonObj.sharedPrefsClass.saveString(
                        "color1",
                        dialogBinding.etClr1.text.toString()
                    )
                    SingletonObj.sharedPrefsClass.saveString(
                        "color2",
                        dialogBinding.etClr2.text.toString()
                    )
                    SingletonObj.sharedPrefsClass.saveInt(
                        "number",
                        dialogBinding.etNumber.text.toString().toInt()
                    )

                    colorCode1 = SingletonObj.sharedPrefsClass.getString("color1")
                    colorCode2 = SingletonObj.sharedPrefsClass.getString("color2")
                    number = SingletonObj.sharedPrefsClass.getInt("number")
                    recyclerView.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }


        }

    }
}