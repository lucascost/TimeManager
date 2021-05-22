package com.webdicas.timemanager

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.webdicas.timemanager.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*


class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener{
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
            show_dialog()
        }
    }
    private fun show_dialog(){
        val dialog= Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.config_task)
        val name:TextView=dialog.findViewById(R.id.task_name)
        var duracao=""

        val hora:NumberPicker=dialog.findViewById(R.id.hora)
        val minuto:NumberPicker=dialog.findViewById(R.id.minuto)

        hora.minValue=0;hora.maxValue=5
        minuto.minValue=0 ; minuto.maxValue=60
        dialog.setCancelable(true)
        dialog.findViewById<Button>(R.id.button).setOnClickListener{
            add_task(name.text.toString(),4)
            dialog.dismiss()
        }

        dialog.show()
        /*
        hora.minValue=0 ; hora.maxValue=24
        minuto.minValue=0 ; minuto.maxValue=60
         */




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
    private fun add_task(nome:String,duracao:Int){
        val panel = findViewById<LinearLayout>(R.id.panel)
        val view = layoutInflater.inflate(R.layout.row,panel,false)
        view.findViewById<TextView>(R.id.name).setText(nome)
        view.findViewById<TextView>(R.id.timer).setText(format(10,0))
        panel.addView(view)
    }
}