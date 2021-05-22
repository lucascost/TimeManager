package com.webdicas.timemanager

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.webdicas.timemanager.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    lateinit var b: ActivityMainBinding
    private var display:TextView? = null
    private var hora_inicio:TextView? = null
    private var hora_fim:TextView? = null
    private var control=0
    private var inicio=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         b=  ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)



        display = findViewById(R.id.main_timer)
        hora_inicio=findViewById(R.id.hora_inicio)
        hora_fim=findViewById(R.id.hora_fim)

        this.findViewById<ImageButton>(R.id.edit).setOnClickListener{
            if(control<2)
                getFullTime()
        }
        b.floatingActionButton.setOnClickListener{
            val panel = findViewById<LinearLayout>(R.id.panel)
            val view = layoutInflater.inflate(R.layout.row,panel,false)
            panel.addView(view)
        }
    }

    private fun getFullTime(){
        val cal:Calendar = Calendar.getInstance()
        var h=cal.get(Calendar.HOUR)
        TimePickerDialog(this,this,h,0,true).show()
        TimePickerDialog(this,this,h,0,true).show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        when(control) {
            0 -> { hora_inicio?.text = format(hourOfDay, minute); inicio=hourOfDay}
            1-> {
                hora_fim?.text = format(hourOfDay, minute)
                config_display(display,inicio,hourOfDay)
            }
        }
        control++
    }
}