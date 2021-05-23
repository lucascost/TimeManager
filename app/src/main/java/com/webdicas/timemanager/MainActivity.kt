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
    private var totalTime=0
    private var control=0
    private var inicio=0
    var hora_inicio=0
    var hora_fim=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         b= ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.edit.setOnClickListener{
            if(control<2)
                getTotalTime()
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

        val hora:NumberPicker=dialog.findViewById(R.id.hora)
        val minuto:NumberPicker=dialog.findViewById(R.id.minuto)

        hora.minValue=0;hora.maxValue=5
        minuto.minValue=0 ; minuto.maxValue=60
        dialog.setCancelable(true)
        dialog.findViewById<Button>(R.id.button).setOnClickListener{
            add_task(name.text.toString(),(hora.value*3600+minuto.value*60)*1000)
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun getTotalTime(){
        val cal:Calendar = Calendar.getInstance()
        var h=cal.get(Calendar.HOUR)
        TimePickerDialog(this,this,h,0,true).show()
        TimePickerDialog(this,this,h,0,true).show()
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        when(control) {
            0 -> { b.horaInicio.text = format(hourOfDay, minute); hora_inicio= convert(hourOfDay,minute)}
            1-> {
                b.horaFim.text = format(hourOfDay, minute)
                hora_fim= convert(hourOfDay,minute)
                totalTime=hora_fim-hora_inicio
                b.mainTimer.text= formatFull(totalTime)
                println(totalTime)
            }
        }
        control++
    }
    private fun add_task(nome:String,duracao:Int){
        val panel = findViewById<LinearLayout>(R.id.panel)
        val view = layoutInflater.inflate(R.layout.row,panel,false)
        val task=Task(view,nome,duracao.toLong())
        panel.addView(view)
    }
}