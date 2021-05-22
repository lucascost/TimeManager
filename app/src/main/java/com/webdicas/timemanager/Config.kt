package com.webdicas.timemanager

import android.widget.TextView
import java.util.*

fun config_display(display: TextView?, inicio:Int, fim:Int){
    display?.text=formatFull((fim-inicio)*3600*1000)
}

fun format(h:Int,m:Int):String{
    return String.format(Locale.getDefault(),"%02d:%02d", h, m)
}

fun formatFull(tempo:Int):String {
    val h = (tempo / 1000) / 3600 as Int
    val minutes = (tempo / 1000 - 3600 * h) / 60 as Int
    val seconds = ((tempo / 1000 - 3600 * h) / 60) % 60 as Int
    val timeLeftFormated = String.format(
        Locale.getDefault(),
        "%02d:%02d:%02d",
        h,
        minutes,
        seconds
    ) //todo ver pra q serve o locale
    return timeLeftFormated
}